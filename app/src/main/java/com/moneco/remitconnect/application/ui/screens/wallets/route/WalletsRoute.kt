package com.moneco.remitconnect.application.ui.screens.wallets.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneco.remitconnect.application.ui.screens.wallets.WalletsScreen
import com.moneco.remitconnect.application.ui.screens.wallets.WalletsViewModel
import com.moneco.remitconnect.application.ui.screens.wallets.state.WalletsActions

@Composable
fun WalletsRoute(viewModel: WalletsViewModel = hiltViewModel()){
    val uiState by viewModel.wallets.collectAsState()
    val actions = rememberWalletsActions(viewModel)

    WalletsScreen(
        state = uiState,
        actions = actions
    )

}

@Composable
fun rememberWalletsActions(viewModel: WalletsViewModel): WalletsActions {
    return remember(viewModel) {
       WalletsActions(
           onWalletSelected = viewModel::onWalletSelected,
           onBack = viewModel::navigateBack,
           onValidate =  viewModel::onContinue
       )
    }
}