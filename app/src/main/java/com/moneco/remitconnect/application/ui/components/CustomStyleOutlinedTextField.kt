package com.moneco.remitconnect.application.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.application.ui.theme.LightGray
import com.moneco.remitconnect.application.ui.theme.LightGray40
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily
import com.moneco.remitconnect.helpers.Dimensions

@Composable
fun CustomStyleOutlinedTextField(
    text: String,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background),
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        placeholder = {
            Text(
                text = placeHolder,
                style = TextStyle(
                    color = Color.LightGray,
                    fontSize = 14.sp,
                    fontFamily = outfitSansFamily,
                    fontWeight = FontWeight.Medium
                )
            )
        },
        singleLine = true,
        readOnly = readOnly,
        shape = MaterialTheme.shapes.small,
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = outfitSansFamily,
            fontWeight = FontWeight.Medium
        )
    )

}