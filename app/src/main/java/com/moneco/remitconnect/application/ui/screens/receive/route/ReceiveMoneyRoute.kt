package com.moneco.remitconnect.application.ui.screens.receive.route

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.moneco.remitconnect.application.ui.screens.receive.ReceiveMoneyScreen
import com.moneco.remitconnect.application.ui.screens.receive.ReceiveMoneyViewModel
import com.moneco.remitconnect.application.ui.screens.receive.state.ReceiveMoneyAction


@OptIn(
    ExperimentalPermissionsApi::class
)
@Composable
fun ReceiveMoneyRoute(viewModel: ReceiveMoneyViewModel = hiltViewModel()) {
    val uiState by viewModel.receiveState.collectAsState()
    val countries by viewModel.countries.collectAsState()
    val contacts by viewModel.contacts.collectAsState()
    val actions = rememberReceiveMoneyActions(viewModel)
    val permissionState = rememberPermissionState(Manifest.permission.READ_CONTACTS)
    if (permissionState.status.isGranted) {
        LaunchedEffect(permissionState){ viewModel.fetchContacts() }
    }else {
        LaunchedEffect(permissionState){
            permissionState.launchPermissionRequest()
        }
    }
    ReceiveMoneyScreen(uiState,countries,contacts,actions)
}

@Composable
fun rememberReceiveMoneyActions(viewModel: ReceiveMoneyViewModel) : ReceiveMoneyAction {
    return remember(viewModel){
        ReceiveMoneyAction(onBack = viewModel::navigateBack,
                           onSelectedItem = viewModel::navigateTo,
                           onQueryTransaction = viewModel::filterTransaction,
                           onValidate = viewModel::onSubmit
            )
    }
}