package com.moneco.remitconnect.application.ui.screens.home.state

/**
 * HomeActions data class defines the actions that can be performed from the HomeScreen.
 *
 * @property onNavigate Callback function that is invoked to handle navigation actions from the HomeScreen.
 */
data class HomeActions(
    val onNavigate : () -> Unit,
)