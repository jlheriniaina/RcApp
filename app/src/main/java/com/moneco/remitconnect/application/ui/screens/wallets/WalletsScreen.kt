package com.moneco.remitconnect.application.ui.screens.wallets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.components.TopBarLeadingButton
import com.moneco.remitconnect.application.ui.screens.wallets.components.WalletsContent
import com.moneco.remitconnect.application.ui.screens.wallets.state.WalletState
import com.moneco.remitconnect.application.ui.screens.wallets.state.WalletsActions

@Composable
fun WalletsScreen(state : WalletState, actions : WalletsActions) {
    Scaffold(
        topBar = {
            TopBarLeadingButton{
                actions.onBack()
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.choose_a_mobile_wallet),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 36.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                )
                when(state){
                    is WalletState.Idle, WalletState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center){
                            CircularProgressIndicator()
                        }
                    }
                    is WalletState.Error -> {
                        Box(modifier = Modifier.fillMaxSize()
                            .padding(24.dp),
                            contentAlignment = Alignment.Center){
                            Text(
                                text = stringResource(R.string.empty_wallets),
                                fontWeight = FontWeight.Thin,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        }
                    }
                    is WalletState.Success -> {
                        WalletsContent(wallets = state.items, actions)
                    }
                }
            }
        },
        containerColor = Color.White
    )
}