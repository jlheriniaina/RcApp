package com.moneco.remitconnect.application.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.domaine.useCase.transaction.GetTransactionsUseCase
import com.moneco.remitconnect.application.domaine.useCase.user.GetUserUseCase
import com.moneco.remitconnect.application.navigation.AppNavigator
import com.moneco.remitconnect.application.ui.screens.home.state.TransactionState
import com.moneco.remitconnect.helpers.Destination
import com.moneco.remitconnect.helpers.NavigationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val appNavigator: AppNavigator,
    private val getUserUseCase: GetUserUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModel() {
    // MutableStateFlow for user data, initially null
    private val _user  = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()
    // MutableStateFlow for transaction state, initially Idle
    private val _transactionState = MutableStateFlow<TransactionState>(TransactionState.Idle)
    val transactionState = _transactionState.asStateFlow()

    // Initialization block to fetch data when ViewModel is created
    init {
        this.fetchData()
    }

    // Function to fetch user data
    fun fetchData() = viewModelScope.launch {

          getUserUseCase.execute(1)
              .collect { value ->
                   _user.value = value
                  fetchTransaction(value.id)
              }
    }
    // Fetch transactions based on user ID
    private fun fetchTransaction(userId : Long) = viewModelScope.launch {
           _transactionState.value = TransactionState.Loading
            getTransactionsUseCase.execute(userId)
               .catch{
                   _transactionState.value = TransactionState.Error(R.string.no_transactions_yet)
               }.collect { data ->
                   if(data.isEmpty()) {
                       _transactionState.value = TransactionState.Error(R.string.no_transactions_yet)
                   } else {
                       _transactionState.value = TransactionState.Success(data.toList())
                   }
           }
    }
    // navigate to the SendMoneyScreen
    fun navigateToSend() = viewModelScope.launch {
        appNavigator.navigateTo(Destination.SendMoneyScreen(), NavigationConstant.HOME)
    }
}