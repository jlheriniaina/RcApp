package com.moneco.remitconnect.application.ui.screens.sending.state

import com.moneco.remitconnect.application.domaine.entites.Transaction

data class SendingActions(
    val onBack: () -> Unit,
    val onSend: (Transaction) -> Unit,
)
