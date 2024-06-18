package com.moneco.remitconnect.application.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.moneco.remitconnect.R


@Composable
fun HomeActionContent() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ItemCard(
                   title =  stringResource(R.string.top_up_balance),
                   icon =  R.drawable.ic_empty_wallet_add
            )
            ItemCard(
                  title = stringResource(R.string.withdrawn_money),
                   icon = R.drawable.ic_wallet_minus
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ItemCard(
                title = stringResource(R.string.get_iban),
                icon =  R.drawable.ic_iban
            )
            ItemCard(
                title = stringResource(R.string.view_analytics),
                icon =  R.drawable.ic_analytics
            )
        }
    }
}