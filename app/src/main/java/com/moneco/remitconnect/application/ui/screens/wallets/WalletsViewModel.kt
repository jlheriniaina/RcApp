package com.moneco.remitconnect.application.ui.screens.wallets

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Wallets
import com.moneco.remitconnect.application.domaine.useCase.wallets.GetWalletsUseCase
import com.moneco.remitconnect.application.navigation.AppNavigator
import com.moneco.remitconnect.application.ui.screens.wallets.state.WalletState
import com.moneco.remitconnect.helpers.COUNTRY_CODE_KEY
import com.moneco.remitconnect.helpers.Destination
import com.moneco.remitconnect.helpers.FIRST_NAME_KEY
import com.moneco.remitconnect.helpers.LAST_NAME_KEY
import com.moneco.remitconnect.helpers.PHONE_NUMBER_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletsViewModel @Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val appNavigator: AppNavigator,
    private val getWalletsUseCase: GetWalletsUseCase
) : ViewModel() {
    private val _wallets = MutableStateFlow<WalletState>(WalletState.Idle)
    val wallets = _wallets.asStateFlow()

    private val _selectedWallet = MutableStateFlow<Wallets?>(null)
    val selectedWallet = _selectedWallet.asStateFlow()

    private val lastname : String? = savedStateHandle[LAST_NAME_KEY]
    private val firstname : String? = savedStateHandle[FIRST_NAME_KEY]
    private val countryCode : String? = savedStateHandle[COUNTRY_CODE_KEY]
    private val phoneNumber : String? = savedStateHandle[PHONE_NUMBER_KEY]

    init {
        this.getWallets()
    }
    private fun getWallets() = viewModelScope.launch {
        _wallets.value = WalletState.Loading
        getWalletsUseCase.execute(Any())
            .catch {
                _wallets.value = WalletState.Error(it.message ?: "")
            }
            .collect {
                _wallets.value = WalletState.Success(it.toList())
            }
    }

    fun onWalletSelected(wallet: Wallets) {
        _selectedWallet.value = wallet
    }

    fun navigateBack() {
        viewModelScope.launch {
            appNavigator.navigateBack()
        }
    }

    fun onContinue() {
        viewModelScope.launch {
            appNavigator.navigateTo(Destination.SendingScreen(
                lastName = lastname ?: "",
                firstName = firstname?: "",
                countryCode = countryCode?:"",
                phone = phoneNumber ?: "",
                walletId = selectedWallet.value?.name ?:"",
            ))
        }
    }


}