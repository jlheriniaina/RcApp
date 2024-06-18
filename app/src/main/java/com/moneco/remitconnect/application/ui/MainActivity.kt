package com.moneco.remitconnect.application.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moneco.remitconnect.application.ui.screens.home.route.HomeRoute
import com.moneco.remitconnect.application.ui.screens.receive.route.ReceiveMoneyRoute
import com.moneco.remitconnect.application.ui.screens.send.route.SendMoneyRoute
import com.moneco.remitconnect.application.ui.screens.send.route.SendToAfricaRoute
import com.moneco.remitconnect.application.ui.screens.sending.route.SendingRoute
import com.moneco.remitconnect.application.ui.screens.succcess.SuccessScreen
import com.moneco.remitconnect.application.ui.screens.wallets.route.WalletsRoute
import com.moneco.remitconnect.application.ui.theme.RemitConnectTheme
import com.moneco.remitconnect.helpers.Destination
import com.moneco.remitconnect.helpers.NavHost
import com.moneco.remitconnect.helpers.NavigationIntent
import com.moneco.remitconnect.helpers.composable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Main activity of the app, serves as the entry point of the app.
 * Uses Hilt for dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Lazily initialize the MainViewModel using the viewModels delegate.
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen(viewModel = viewModel)
        }
    }

    override fun onResume() {
        super.onResume()
    }
}

/**
 * Composable function representing the main screen of the app.
 * Sets up navigation and theme.
 *
 * @param viewModel The MainViewModel instance used by this screen.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel){
    val navController = rememberNavController()
    // Handle navigation effects
    NavigationEffects(
        navigationChannel = viewModel.navigationChannel,
        navHostController = navController
    )
    // Apply the app's theme
    RemitConnectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            // Define composable destinations for navigation

            NavHost(
                navController = navController,
                startDestination  = Destination.HomeScreen
            )
            {
                composable(destination = Destination.HomeScreen) {
                    HomeRoute()
                }
                composable(destination = Destination.SendMoneyScreen) {
                    SendMoneyRoute()
                }
                composable(destination = Destination.SendToAfrica) {
                    SendToAfricaRoute()
                }
                composable(destination = Destination.ReceiveMoneyScreen) {
                    ReceiveMoneyRoute()
                }
                composable(destination = Destination.WalletScreen) {
                   WalletsRoute()
                }
                composable(destination = Destination.SendingScreen) {
                    SendingRoute()
                }
                composable(destination = Destination.SuccessScreen){
                    SuccessScreen(viewModel)
                }
            }
        }
    }
}

/**
 * Composable function to handle navigation effects.
 *
 * @param navigationChannel The channel that emits navigation intents.
 * @param navHostController The NavController for managing navigation.
 */
@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        // Collect navigation intents from the channel and handle then
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null)
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    else
                        navHostController.popBackStack()
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {

                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}
