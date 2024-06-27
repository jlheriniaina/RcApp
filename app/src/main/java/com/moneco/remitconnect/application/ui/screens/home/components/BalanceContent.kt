package com.moneco.remitconnect.application.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.theme.ColorPrimary
import com.moneco.remitconnect.application.ui.theme.ColorPrimaryDark
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily


@Composable
fun BalanceContent(balance : Double) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                6.dp,
                MaterialTheme.shapes.medium,
                ambientColor = ColorPrimaryDark,
                spotColor = ColorPrimary
            )
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        colorResource(id = R.color.gradient_start),
                        colorResource(id = R.color.gradient_end)
                    ),
                    start = Offset(0.5f, 0.5f),
                    end = Offset.Infinite
                )
            )
    ) {
        Row(
            modifier= Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(
                        top = 36.dp, bottom = 36.dp
                    )
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(R.string.your_balance),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontFamily = outfitSansFamily,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "320,000",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White,
                    fontFamily = outfitSansFamily,
                    fontWeight =FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.us_dollars),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontFamily = outfitSansFamily,
                    fontWeight = FontWeight.Light
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .offset(y = 32.dp)
                    .background(
                        color = Color(1.0f, 1.0f, 1.0f, 0.2f),
                        shape = RoundedCornerShape(16.dp)
                    ), contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_balance),
                    contentDescription = stringResource(id = R.string.your_balance),
                    tint = Color.White
                )
            }
        }
    }
}