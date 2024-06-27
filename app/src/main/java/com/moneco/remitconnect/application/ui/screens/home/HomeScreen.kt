package com.moneco.remitconnect.application.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.ui.components.ChildLayout
import com.moneco.remitconnect.application.ui.components.NavigationBarContent
import com.moneco.remitconnect.application.ui.components.VerticalScrollLayout
import com.moneco.remitconnect.application.ui.screens.home.components.BalanceContent
import com.moneco.remitconnect.application.ui.screens.home.components.HomeActionContent
import com.moneco.remitconnect.application.ui.screens.home.components.HomeTopBar
import com.moneco.remitconnect.application.ui.screens.home.components.TransactionContent
import com.moneco.remitconnect.application.ui.screens.home.state.HomeActions
import com.moneco.remitconnect.application.ui.screens.home.state.TransactionState
import com.moneco.remitconnect.helpers.Dimensions

/**
 * HomeScreen composable function renders the home screen of the application.
 *
 * @param user The user data to be displayed, which can be null. This includes details such as the user's name and balance.
 * @param state The current state of transactions, which can be loading, success with transaction data, or an error state.
 * @param action The actions or callbacks that handle user interactions, such as navigation and other events.
 */
@Composable
fun HomeScreen(user: User?, state: TransactionState, action : HomeActions) {
    Scaffold(
        topBar = {
            HomeTopBar(tile = user?.name ?: "jlh")
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .background(Color(0xFFF8FCFB))
            ) {
                VerticalScrollLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Dimensions.size_m()),
                    ChildLayout(
                        contentType = "Header",
                        content = {
                            Column(Modifier.fillMaxWidth()){
                                BalanceContent(balance = user?.balance ?: 0.0)
                                Spacer(
                                    modifier = Modifier.height(Dimensions.size_m())
                                )
                            }
                        }),
                    ChildLayout(
                        contentType = "Action",
                        content = {
                            Column(Modifier.fillMaxWidth()){
                                Spacer(
                                    modifier = Modifier.height(Dimensions.size_m())
                                )
                                HomeActionContent()

                            }
                        }),
                    ChildLayout(
                        contentType = "list",
                        content = {
                            TransactionContent(state = state)
                        }),
                )
            }
        },
       bottomBar = {
           NavigationBarContent {
               action.onNavigate()
           }
       }
    )
}