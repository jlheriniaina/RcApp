package com.moneco.remitconnect.application.ui.screens.sending.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.theme.DuskGray
import com.moneco.remitconnect.application.ui.theme.midnightBlue
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily

@SuppressLint("DefaultLocale")
@Composable
fun AmountContent(amount : Double) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.recipient_gets),
            fontSize = 14.sp,
            color = DuskGray,
            fontWeight = FontWeight.Medium,
            fontFamily = outfitSansFamily,
        )
        Text(
            text = "$${String.format("%.2f", amount.times(655.94))} XOF",
            fontSize = 18.sp,
            color = midnightBlue,
            fontWeight = FontWeight.Medium,
            fontFamily = outfitSansFamily,
        )
    }
}