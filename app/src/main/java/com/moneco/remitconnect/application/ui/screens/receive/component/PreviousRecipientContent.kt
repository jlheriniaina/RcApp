package com.moneco.remitconnect.application.ui.screens.receive.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.ui.components.SearchBar
import com.moneco.remitconnect.application.ui.screens.receive.state.ReceiveMoneyAction
import com.moneco.remitconnect.application.ui.screens.receive.state.ReceiveTransactionState
import com.moneco.remitconnect.application.ui.theme.BabyBlue
import com.moneco.remitconnect.application.ui.theme.LightGray

@Composable
fun PreviousRecipientContent(state : ReceiveTransactionState,
                             actions : ReceiveMoneyAction) {
    val searchQuery = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        SearchBar(searchQuery.value) { query ->
            searchQuery.value = query
            actions.onQueryTransaction(query)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.contacts_on_your_phone),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 21.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            when(state){
                is ReceiveTransactionState.Idle,ReceiveTransactionState.Loading -> {
                    item {
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center){
                            CircularProgressIndicator()
                        }
                    }
                }
                is ReceiveTransactionState.Success -> {
                    items(state.items) { item ->
                        RecipientContentItem(item = item){
                            actions.onSelectedItem(it)
                        }
                    }
                }
                is ReceiveTransactionState.Empty -> {
                    if (searchQuery.value.isNotEmpty()){
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_empty_receive),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp)
                                        .wrapContentHeight(),
                                    contentScale = ContentScale.Fit
                                )
                                Text(text = stringResource(R.string.no_matching_results_found))
                            }
                        }
                    }else {
                        item {
                            Text(
                                text = stringResource(R.string.no_contact_saved),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecipientContentItem(item : Transaction,
                         onItemClick: (Transaction) -> Unit){
    Column(Modifier.fillMaxWidth()){
        Row(
            modifier = Modifier
                .clickable {
                    onItemClick(item)
                }
                .height(77.dp)
                .fillMaxWidth()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .fillMaxWidth()
                    .background(
                        color = BabyBlue,
                        shape = RoundedCornerShape(12.dp)
                    ),
                painter = painterResource(id = R.drawable.ic_coin),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "${item.firstName} ${item.lastName}",
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = "${item.country} ${item.phoneNumber}",
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                contentDescription = "",
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = LightGray
        )
    }
}