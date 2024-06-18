package com.moneco.remitconnect.application.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.theme.ColorPrimary
import com.moneco.remitconnect.application.ui.theme.LightGray
import com.moneco.remitconnect.application.ui.theme.MintGreen
import com.moneco.remitconnect.application.ui.theme.PaleMint

@Composable
fun ValidateButton(title : String, isEnable : Boolean, onPressedCallback: () -> Unit) {
    Button(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnable) ColorPrimary else LightGray
        ),
        enabled = isEnable,
        onClick = { onPressedCallback() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )
    }
}
@Composable
fun ContactButton(onClick : () -> Unit) {
    Button(
        onClick = {
              onClick()
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .size(327.dp, 48.dp)
            .border(
                1.dp, color = MintGreen,
                shape = RoundedCornerShape(8.dp)
            ),
        colors = ButtonDefaults.buttonColors(
           PaleMint
        ),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_contact),
                contentDescription = ""
            )
            Text(
                text = stringResource(R.string.choose_a_contact),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 24.sp
            )
        }
    }
}