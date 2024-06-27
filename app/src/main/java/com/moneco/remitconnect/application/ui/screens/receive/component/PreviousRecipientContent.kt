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
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily

@Composable
fun PreviousRecipientContent(state : ReceiveTransactionState,
                             actions : ReceiveMoneyAction) {
    val searchQuery = remember { mutableStateOf("") }

    Column(
        modifier = Modifier

            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        SearchBar(searchQuery.value) { query ->
            searchQuery.value = query
            actions.onQueryTransaction(query)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier =  Modifier.padding(horizontal = 24.dp),
            text = stringResource(R.string.contacts_on_your_phone),
            fontSize = 14.sp,
            fontFamily = outfitSansFamily,
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
                    itemsIndexed(state.items) { index,item ->
                        RecipientContentItem(item = item, index = index){
                            actions.onSelectedItem(it)
                        }
                        HorizontalDivider(thickness = 1.dp)
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
fun RecipientContentItem(item : Transaction, index: Int,
                         onItemClick: (Transaction) -> Unit){

    Column(Modifier.fillMaxWidth()){
        Row(
            modifier = Modifier
                .clickable {
                    onItemClick(item)
                }
                .height(77.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    ),
                painter = painterResource(id = getImage(index)),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = "${item.firstName} ${item.lastName}",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = outfitSansFamily,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
                Text(
                    text = "${item.country} ${item.phoneNumber}",
                    fontWeight = FontWeight.Medium,
                    fontFamily = outfitSansFamily,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                contentDescription = "",
            )
        }
    }
}

fun getImage(index : Int) : Int{
    return when(index){
        1  -> R.drawable.img1
        2 -> R.drawable.img2
        3 -> R.drawable.img3
        4 -> R.drawable.img4
        5 -> R.drawable.img5
        6 -> R.drawable.img6
        7 -> R.drawable.img7
        8 -> R.drawable.img8
        else -> R.drawable.img1
    }
}
