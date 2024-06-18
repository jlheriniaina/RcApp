package com.moneco.remitconnect.application.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.theme.LightGray


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(tile: String) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hello, $tile",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W600,
                    color = Color(0xFF00122C)
                )
                Box(
                    modifier = Modifier
                        .clickable { }
                        .size(40.dp)
                        .background(
                            color = LightGray,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_bell),
                        contentDescription = "bell",
                        tint =  Color(0xFF00122C)

                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            MaterialTheme.colorScheme.secondary
        )
    )
}