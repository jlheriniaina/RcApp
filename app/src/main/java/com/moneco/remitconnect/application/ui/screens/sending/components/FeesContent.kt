package com.moneco.remitconnect.application.ui.screens.sending.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.application.ui.theme.DuskGray
import com.moneco.remitconnect.application.ui.theme.midnightBlue
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily

@Composable
fun FeesContent() {
    val data = listOf(
        "Moneco fees" to "0.0 EUR",
        "Transfer fees" to "0.0 EUR",
        "Conversion rate" to "571.00 XOF",
        "You'll spend in total" to "0.0 EUR"
    )
    data.forEach {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = it.first,
                fontSize = 14.sp,
                color = DuskGray,
                fontWeight = FontWeight.Medium,
                fontFamily = outfitSansFamily,
            )
            Text(
                text = it.second,
                fontSize = 14.sp,
                color = midnightBlue,
                fontWeight = FontWeight.Medium,
                fontFamily = outfitSansFamily,
            )
        }
    }

}