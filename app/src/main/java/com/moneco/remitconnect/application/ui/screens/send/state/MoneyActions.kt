package com.moneco.remitconnect.application.ui.screens.send.state
import com.moneco.remitconnect.application.ui.screens.send.money.SendingOption

data class MoneyActions(
    val onSelectedItem : (SendingOption) -> Unit,
    val onBackPressed : () -> Unit
)
