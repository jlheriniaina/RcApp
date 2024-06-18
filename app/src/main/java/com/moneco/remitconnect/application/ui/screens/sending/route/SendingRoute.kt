package com.moneco.remitconnect.application.ui.screens.sending.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneco.remitconnect.application.ui.screens.sending.SendingScreen
import com.moneco.remitconnect.application.ui.screens.sending.SendingViewModel
import com.moneco.remitconnect.application.ui.screens.sending.state.SendingActions


@Composable
fun SendingRoute(viewModel: SendingViewModel = hiltViewModel()){
    val user by viewModel.user.collectAsState()
    val transaction by viewModel.transaction.collectAsState()
    SendingScreen(user, transaction, actions = rememberSending(viewModel))
}


@Composable
fun rememberSending(viewModel: SendingViewModel) : SendingActions {
    return remember(viewModel){
            SendingActions(
                  onSend = viewModel::onSubmit,
                  onBack = viewModel::navigateBack
            )
    }
}