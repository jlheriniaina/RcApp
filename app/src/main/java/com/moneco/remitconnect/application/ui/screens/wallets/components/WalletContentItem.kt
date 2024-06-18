package com.moneco.remitconnect.application.ui.screens.wallets.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.application.domaine.entites.Wallets
import com.moneco.remitconnect.application.ui.theme.LightGray
import com.moneco.remitconnect.application.ui.theme.PaleMint

@Composable
fun WalletContentItem(item  : Wallets,
                      onSelected: (Wallets) -> Unit, selected: Boolean) {

    Surface(
        shape = MaterialTheme.shapes.small,
        color = if (selected) PaleMint else Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else LightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 8.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable { onSelected(item) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(
                    id = item.logo()
                ),
                contentDescription = ""
            )
            Text(
                text = item.name,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
            )
        }
    }
}