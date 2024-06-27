package com.moneco.remitconnect.application.ui.screens.home.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.ui.screens.home.state.TransactionState
import com.moneco.remitconnect.application.ui.theme.BabyBlue
import com.moneco.remitconnect.application.ui.theme.CeruleanBlue
import com.moneco.remitconnect.application.ui.theme.midnightBlue
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily
import com.moneco.remitconnect.helpers.currency

@Composable
fun TransactionContent(state : TransactionState) {
    Column(
        Modifier
            .fillMaxSize()
    ){
        Text(
            text = stringResource(R.string.transactions),
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
            color = midnightBlue,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ){
            when (state){
                TransactionState.Idle, TransactionState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        CircularProgressIndicator()
                    }
                }
                is TransactionState.Error -> {
                    EmptyTransaction(message = state.message)
                }
                is TransactionState.Success -> {
                    LazyColumn(modifier = Modifier
                        .height((100 * state.items.count()).dp)
                        .padding(vertical = 16.dp)){
                        items(state.items){
                            TransactionItemCard(
                                transaction = it
                            )
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = Color.LightGray
                            )
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun TransactionItemCard(transaction: Transaction){
    Row(
        modifier = Modifier
            .height(74.dp)
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .fillMaxWidth()
                .background(
                    color = BabyBlue,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_up_right),
                contentDescription = stringResource(R.string.sent),
                tint = CeruleanBlue
            )
        }
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Column(
            Modifier.weight(1f)
        ) {
            Text(stringResource(R.string.sent_to),
                fontWeight = FontWeight.Thin,
                fontFamily = outfitSansFamily,
                fontSize = 12.sp)

            Text("${transaction.firstName} ${transaction.lastName}",
                 fontWeight = FontWeight.Medium,
                fontFamily = outfitSansFamily,
                fontSize = 16.sp
                )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }
        Text(text = transaction.amount.currency(),
             fontWeight = FontWeight.Medium)
    }
}

@Composable
fun EmptyTransaction(message : Int){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Text(
            text = stringResource(message),
            fontWeight = FontWeight.Thin,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}