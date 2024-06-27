package com.moneco.remitconnect.application.ui.screens.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneco.remitconnect.application.ui.screens.home.HomeScreen
import com.moneco.remitconnect.application.ui.screens.home.HomeViewModel
import com.moneco.remitconnect.application.ui.screens.home.state.HomeActions

/**
 * HomeRoute is a composable function that sets up the home screen with the required data and actions.
 *
 * @param viewModel The HomeViewModel instance provided by Hilt for dependency injection.
 */
@Composable
fun HomeRoute(viewModel: HomeViewModel = hiltViewModel()) {
    // Collect user data from the ViewModel as a state
    val user by viewModel.user.collectAsState()
    // Collect transaction state from the ViewModel as a state
    val transactionState by viewModel.transactionState.collectAsState()
    // Create and remember the user actions for the HomeScreen
    val actions = rememberUserActions(viewModel)
    // Render the HomeScreen with the collected user data, transaction state, and actions
    HomeScreen(user, transactionState, actions)
}

/**
 * rememberUserActions is a composable function that creates and remembers the actions for the HomeScreen.
 *
 * @param viewModel The HomeViewModel instance to bind the actions.
 * @return HomeActions containing the actions to be used in the HomeScreen.
 */
@Composable
fun rememberUserActions(viewModel: HomeViewModel) : HomeActions {
    return remember(viewModel){
        HomeActions(onNavigate = viewModel::navigateToSend)
    }
}