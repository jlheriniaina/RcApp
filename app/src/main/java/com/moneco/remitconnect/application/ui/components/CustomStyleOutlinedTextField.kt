package com.moneco.remitconnect.application.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.application.ui.theme.LightGray40

@Composable
fun CustomStyleOutlinedTextField(
    text: String,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
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
                    fontSize = 14.sp
                )
            )
        },
        singleLine = true,
        readOnly = readOnly,
        shape = MaterialTheme.shapes.large,
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black,
            fontSize = 14.sp
        ),
        colors = TextFieldDefaults.colors(
            cursorColor =  Color.Black,
            unfocusedContainerColor = LightGray40,
            focusedContainerColor =  LightGray40,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}