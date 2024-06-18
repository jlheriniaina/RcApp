package com.moneco.remitconnect.application.ui.screens.send.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneco.remitconnect.application.ui.screens.send.money.SendMoneyScreen
import com.moneco.remitconnect.application.ui.screens.send.money.SendMoneyViewModel
import com.moneco.remitconnect.application.ui.screens.send.state.MoneyActions


@Composable
fun SendMoneyRoute(viewModel: SendMoneyViewModel = hiltViewModel()) {
    SendMoneyScreen(data = viewModel.items, action = rememberSendMoneyActions(viewModel))
}

@Composable
fun rememberSendMoneyActions(viewModel: SendMoneyViewModel) : MoneyActions {
    return remember(viewModel){
            MoneyActions(onSelectedItem = viewModel::navigateTo,
                         onBackPressed = viewModel::navigateBack,)
    }
}