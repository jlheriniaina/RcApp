package com.moneco.remitconnect.application.ui.screens.sending.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.ui.theme.DuskGray
import com.moneco.remitconnect.application.ui.theme.midnightBlue
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily
import com.moneco.remitconnect.helpers.formatAmountToXOF

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendContentPicker(transaction : Transaction,
                      onDismiss : () -> Unit, onValidate : (Transaction) -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Text(
                text = "Confirm transfer",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = outfitSansFamily,
                color = Color(0xFF00122C),
                lineHeight = 36.sp,
            )
            InfoTransactionContent(
                title = stringResource(id = R.string.transaction_amount),
                value = transaction.amount.formatAmountToXOF()
            )

            InfoTransactionContent(
                title = stringResource(id = R.string.transaction_to),
                value = "${transaction.firstName} ${transaction.lastName}"
            )

            InfoTransactionContent(
                title = stringResource(id = R.string.transaction_from),
                value = "${transaction.wallet} : ${transaction.country} ${transaction.phoneNumber}"
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = {
                       onValidate(transaction)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(311.dp, 56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Confirm")
                }
            }
        }
    }
}

@Composable
fun InfoTransactionContent(title : String, value : String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = DuskGray,
            fontWeight = FontWeight.Medium,
            fontFamily = outfitSansFamily,
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = outfitSansFamily,
            color = midnightBlue
        )
    }
}