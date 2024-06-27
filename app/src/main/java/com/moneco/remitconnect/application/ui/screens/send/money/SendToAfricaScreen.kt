package com.moneco.remitconnect.application.ui.screens.send.money

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import com.moneco.remitconnect.application.ui.components.TopBarLeadingButton
import com.moneco.remitconnect.application.ui.screens.send.state.MoneyActions
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily


@Composable
fun SendToAfricaScreen(data : List<SendingOption>, action: MoneyActions) {

    Scaffold(
        topBar = {
           TopBarLeadingButton {
              action.onBackPressed()
           }
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = stringResource(R.string.send_to_africa),
                        fontSize = 24.sp,
                        fontFamily = outfitSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF00122C),
                        lineHeight = 36.sp,
                        modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider(thickness = 0.7.dp)
                    LazyColumn(
                        Modifier.padding(vertical = 16.dp)
                    ){
                        items(data) { item ->
                            MoneyContentItem(item = item) {
                                action.onSelectedItem(item)
                            }
                            HorizontalDivider(thickness = 0.7.dp)
                        }
                    }
                }
            }
        },
        containerColor = Color.White
    )
}