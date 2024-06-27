package com.moneco.remitconnect.application.ui.screens.home.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.screens.home.state.ActionMenu


@Composable
fun HomeActionContent() {
    val actions = listOf(
        ActionMenu(
            title = R.string.top_up_balance,
            icon = R.drawable.ic_empty_wallet_add
        ),
        ActionMenu(
            title = R.string.withdrawn_money,
            icon = R.drawable.ic_wallet_minus
        ),
        ActionMenu(
            title = R.string.get_iban,
            icon = R.drawable.ic_iban
        ),
        ActionMenu(
            title = R.string.view_analytics,
            icon = R.drawable.ic_analytics
        )
    )
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        columns = StaggeredGridCells.Fixed(count = 2),
        userScrollEnabled = false,
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(actions) { item ->
            ItemCard(
                title =  stringResource(item.title),
                icon =  item.icon
            )
        }
    }
}