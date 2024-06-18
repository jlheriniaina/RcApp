package com.moneco.remitconnect.application.ui.screens.wallets.state

import com.moneco.remitconnect.application.domaine.entites.Wallets

data class WalletsActions(
    val onWalletSelected : (Wallets) -> Unit,
    val onBack : () -> Unit,
    val onValidate : () -> Unit
)
