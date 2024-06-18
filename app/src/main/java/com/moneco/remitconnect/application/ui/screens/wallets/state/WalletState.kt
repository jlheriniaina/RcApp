package com.moneco.remitconnect.application.ui.screens.wallets.state

import com.moneco.remitconnect.application.domaine.entites.Wallets

sealed class WalletState {
    data object Idle : WalletState()
    data object Loading : WalletState()
    data class Success(val items  : List<Wallets>) : WalletState()
    data class Error(val message: String) : WalletState()

}