package com.moneco.remitconnect.application.ui.screens.sending

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.domaine.useCase.transaction.CreateTransactionUseCase
import com.moneco.remitconnect.application.domaine.useCase.user.GetUserUseCase
import com.moneco.remitconnect.application.navigation.AppNavigator
import com.moneco.remitconnect.helpers.COUNTRY_CODE_KEY
import com.moneco.remitconnect.helpers.Destination
import com.moneco.remitconnect.helpers.FIRST_NAME_KEY
import com.moneco.remitconnect.helpers.LAST_NAME_KEY
import com.moneco.remitconnect.helpers.NavigationConstant
import com.moneco.remitconnect.helpers.PHONE_NUMBER_KEY
import com.moneco.remitconnect.helpers.WALLET_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SendingViewModel
@Inject constructor(savedStateHandle: SavedStateHandle,
                    private val appNavigator: AppNavigator,
                    private val getUserUseCase: GetUserUseCase,
                    private val createTransactionUseCase: CreateTransactionUseCase
) : ViewModel()  {
    private val lastname : String? = savedStateHandle[LAST_NAME_KEY]
    private val firstname : String? = savedStateHandle[FIRST_NAME_KEY]
    private val countryCode : String? = savedStateHandle[COUNTRY_CODE_KEY]
    private val phoneNumber : String? = savedStateHandle[PHONE_NUMBER_KEY]
    private val wallet : String? = savedStateHandle[WALLET_KEY]

    private val _user  = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    private val _transaction = MutableStateFlow(Transaction(
                         firstName = firstname ?: "",
                         lastName = lastname?: "",
                         phoneNumber = phoneNumber?: "",
                         amount = 0.0,
                         country = countryCode ?: "",
                         userId =  -1,
                         wallet = wallet ?: "Mobile Money"
    ))
    val transaction = _transaction.asStateFlow()
    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        getUserUseCase.execute(1)
            .collect { value ->
                _user.value = value
                _transaction.value.userId = value.id
            }
    }
    fun onSubmit(transaction : Transaction) = viewModelScope.launch {
        createTransactionUseCase.execute(transaction)
        appNavigator.navigateTo(Destination.SuccessScreen(), NavigationConstant.HOME)
    }

    fun navigateBack() = viewModelScope.launch {
        appNavigator.navigateBack()
    }
}