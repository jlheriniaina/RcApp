package com.moneco.remitconnect.application.ui.screens.send.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.screens.send.money.SendingOption
import com.moneco.remitconnect.application.ui.theme.ColorPrimary
import com.moneco.remitconnect.application.ui.theme.PaleMint

@Composable
fun MoneyContentItem(item : SendingOption, onSelected : (SendingOption) -> Unit) {
   Column {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .background(Color.White)
               .padding(16.dp)
               .clickable { onSelected(item) },
           verticalAlignment = Alignment.CenterVertically,
       ) {
           Box(
               modifier = Modifier
                   .size(40.dp)
                   .background(
                       color = PaleMint,
                       shape = RoundedCornerShape(12.dp)
                   ),
               contentAlignment = Alignment.Center
           ) {
               Icon(
                   painter = painterResource(id = item.icon),
                   contentDescription = item.title,
                   tint = ColorPrimary
               )
           }
           Row(
               Modifier
                   .fillMaxWidth()
                   .padding(start = 16.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   text = item.title,
                   fontWeight = FontWeight.W500,
                   fontSize = 16.sp,
                   lineHeight = 20.8.sp
               )
               Icon(
                   imageVector = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                   contentDescription = "",
               )
           }
       }
       HorizontalDivider(thickness = 0.5.dp)
   }

}