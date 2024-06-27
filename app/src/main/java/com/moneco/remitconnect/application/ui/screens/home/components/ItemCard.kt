package com.moneco.remitconnect.application.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.theme.ColorPrimary
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily
import com.moneco.remitconnect.helpers.Dimensions

@Composable
fun ItemCard(title :String, icon : Int) {
    Card(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.size_menu_item))
            .width(dimensionResource(id = R.dimen.size_menu_item)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .padding(
                    horizontal = Dimensions.size_m(),
                    vertical = Dimensions.size_sm()
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = Color(0xFFEDF8F5),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(id = icon),
                    contentDescription = title,
                    tint = Color(0xFF08A075)
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.size_m()))
            Row(modifier = Modifier.width(IntrinsicSize.Max)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = title,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = outfitSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = Dimensions.text_m(),
                        color = Color(0xFF007554)
                    )
                )
            }
            Spacer(modifier = Modifier.height(Dimensions.size_s()))
        }
    }
}