package com.moneco.remitconnect.application.ui.screens.send.money

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.screens.send.component.MoneyContentItem
import com.moneco.remitconnect.application.ui.screens.send.component.TopBarTrailingButton
import com.moneco.remitconnect.application.ui.screens.send.state.MoneyActions


@Composable
fun SendMoneyScreen(data : List<SendingOption>, action : MoneyActions){
    Scaffold(
        topBar = {
            TopBarTrailingButton{
                action.onBackPressed()
            }
        },
        content = { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.send_money),
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 36.sp,
                        modifier = Modifier.padding(start = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyColumn(
                        Modifier.padding(16.dp)
                    ){
                        items(data) { item ->
                            MoneyContentItem(item = item) {
                                action.onSelectedItem(item)
                            }
                        }
                    }
                }
            }
        },
        containerColor = Color.White
    )
}