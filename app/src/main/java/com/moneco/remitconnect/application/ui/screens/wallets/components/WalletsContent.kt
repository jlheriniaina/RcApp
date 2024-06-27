package com.moneco.remitconnect.application.ui.screens.wallets.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Wallets
import com.moneco.remitconnect.application.ui.components.ValidateButton
import com.moneco.remitconnect.application.ui.screens.wallets.state.WalletsActions


@Composable
fun WalletsContent(wallets : List<Wallets>, actions : WalletsActions) {
    var selectedIndex by remember { mutableIntStateOf(-1) }


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (content, footer) = createRefs()
        LazyColumn(
            Modifier
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 24.dp)){
            itemsIndexed(wallets){ index, item ->
                WalletContentItem(item = item,
                    onSelected = {
                        selectedIndex = index
                        actions.onWalletSelected(it)
                    },
                    selected = selectedIndex == index)
            }
        }
        Box(
            modifier = Modifier
                .constrainAs(footer) {
                    bottom.linkTo(parent.bottom, margin = -24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(
                    16.dp, RoundedCornerShape(
                        topStart = 4.dp, topEnd = 4.dp,
                    )
                )
                .background(
                    Color.White,
                    RoundedCornerShape(
                        topStart = 16.dp, topEnd = 16.dp,

                    )
                ),
        ) {
            ValidateButton(title = stringResource(id = R.string.continue_txt),
                isEnable = selectedIndex != -1){
                actions.onValidate()
            }
        }
    }
}