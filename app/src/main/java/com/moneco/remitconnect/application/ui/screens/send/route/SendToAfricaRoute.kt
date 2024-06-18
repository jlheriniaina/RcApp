package com.moneco.remitconnect.application.ui.screens.send.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneco.remitconnect.application.ui.screens.send.money.SendMoneyViewModel
import com.moneco.remitconnect.application.ui.screens.send.money.SendToAfricaScreen
import com.moneco.remitconnect.application.ui.screens.send.state.MoneyActions

@Composable
fun SendToAfricaRoute(viewModel: SendMoneyViewModel = hiltViewModel()) {
    SendToAfricaScreen(data = viewModel.itemsTypes, action = rememberSendToAfricaActions(viewModel))
}

@Composable
fun rememberSendToAfricaActions(viewModel: SendMoneyViewModel) : MoneyActions {
    return remember(viewModel){
        MoneyActions(onSelectedItem = viewModel::navigateTo,
            onBackPressed = viewModel::navigateBack,)
    }
}