package com.moneco.remitconnect.application.ui.screens.receive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneco.remitconnect.application.domaine.entites.Contact
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.domaine.useCase.contact.GetContactUseCase
import com.moneco.remitconnect.application.domaine.useCase.country.GetCountryUseCase
import com.moneco.remitconnect.application.domaine.useCase.transaction.FilterTransactionUseCase
import com.moneco.remitconnect.application.domaine.useCase.transaction.GetTransactionsUseCase
import com.moneco.remitconnect.application.domaine.useCase.user.GetUserUseCase
import com.moneco.remitconnect.application.navigation.AppNavigator
import com.moneco.remitconnect.application.ui.screens.receive.state.QueryTransaction
import com.moneco.remitconnect.application.ui.screens.receive.state.ReceiveTransactionState
import com.moneco.remitconnect.helpers.Destination
import com.moneco.remitconnect.helpers.NavigationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ReceiveMoneyViewModel
@Inject
constructor(private val appNavigator: AppNavigator,
            private val getUserUseCase: GetUserUseCase,
            private val getTransactionsUseCase: GetTransactionsUseCase,
            private val filterTransactionUseCase: FilterTransactionUseCase,
            private val getCountryUseCase: GetCountryUseCase,
            private val getContactUseCase: GetContactUseCase
) : ViewModel(){

    private val _user  = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()
    private val _receiveState = MutableStateFlow<ReceiveTransactionState>(ReceiveTransactionState.Idle)
    val receiveState = _receiveState.asStateFlow()

    private val _countries  = MutableStateFlow<List<Country>>(listOf())
    val countries = _countries.asStateFlow()
    private val _contacts  = MutableStateFlow<Map<String, List<Contact>>>(mapOf())
    val contacts = _contacts.asStateFlow()

    init {
        fetchData()
        fetchCountry()
    }
    private fun fetchData() = viewModelScope.launch {
        getUserUseCase.execute(1)
            .collect { value ->
                _user.value = value
                fetchTransaction(value.id)
            }
    }

   private fun fetchCountry() = viewModelScope.launch {
       getCountryUseCase.execute(Any())
           .collect { value ->
               _countries.value = value
           }
   }

    fun fetchContacts() = viewModelScope.launch {
        getContactUseCase.execute(Any())
            .collect { value ->
                _contacts.value = groupContacts(value)
            }
    }
    private fun fetchTransaction(userId : Long) = viewModelScope.launch {
        _receiveState.value = ReceiveTransactionState.Loading
        getTransactionsUseCase.execute(userId)
            .catch{
                _receiveState.value = ReceiveTransactionState.Empty
            }.collect { data ->
                if(data.isEmpty()) {
                    _receiveState.value = ReceiveTransactionState.Empty
                } else {
                    _receiveState.value = ReceiveTransactionState.Success(data.toList())
                }
            }
    }

    fun filterTransaction(query: String) = viewModelScope.launch {
        filterTransactionUseCase
            .execute(QueryTransaction(id = user.value?.id?: -1, term = query))
            .catch {
                _receiveState.value = ReceiveTransactionState.Empty
            }.collect { data ->
                val result = data.ifEmpty {
                    listOf(
                        Transaction(id = 1,
                            country = "Mada",
                            phoneNumber = "0346792869", firstName = "Jean Luc",
                            lastName = "Heriniaina",
                            userId = 1L, amount = 200.0,
                            wallet = "Wave"),
                    )
                }
                if(result.isEmpty()) {
                    _receiveState.value = ReceiveTransactionState.Empty
                } else {
                    _receiveState.value = ReceiveTransactionState.Success(result.toMutableList())
                }
            }
    }

    private suspend fun groupContacts(contacts: List<Contact>): Map<String, List<Contact>> {
        return withContext(Dispatchers.IO) {
            contacts.groupBy { it.name.first().toString() }
        }
    }

    fun navigateBack() = viewModelScope.launch {
        appNavigator.navigateBack()
    }
    fun navigateTo(item : Transaction) {
        onSubmit(item.phoneNumber, item.firstName, item.lastName, item.country)
    }

    fun onSubmit(phoneNumber : String, firstName : String, lastName : String, countryCode : String) = viewModelScope.launch {
         appNavigator.navigateTo(Destination.WalletScreen(
             phone = phoneNumber,
             firstName = firstName,
             lastName = lastName, userId = user.value?.id ?: -1,
             countryCode = countryCode), NavigationConstant.SEND_MONEY_OPTION)
    }
}