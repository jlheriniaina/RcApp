package com.moneco.remitconnect.application.ui.screens.receive.state

import com.moneco.remitconnect.application.domaine.entites.Transaction

data class ReceiveMoneyAction(
    val onQueryTransaction: (String) -> Unit,
    val onBack: () -> Unit,
    val onSelectedItem : (Transaction) -> Unit,
    val onValidate : (String,String,String, String) -> Unit
)
