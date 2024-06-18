package com.moneco.remitconnect.application.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.application.ui.theme.ColorPrimary

@Composable
fun TabsCustomComponent(
    tabs: List<String>,
    modifier: Modifier,
    onSelected: (Int) -> Unit
) {
    // State to keep track of the selected tab
    var selectedOption by remember { mutableStateOf(tabs[0]) }

    // Surface composable to create a background for tabs
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 3.dp,
        modifier = modifier
            .fillMaxWidth(),
        color = Color.Transparent

    ) {
        // Row to display the tabs horizontally
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            // Iterate over each tab
            tabs.forEachIndexed { index,  text ->
                // Text composable representing each tab
                Text(
                    text = text,
                    color = if (text == selectedOption) Color.White else ColorPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .clickable {
                            // Update the selected tab and invoke the callback
                            selectedOption = text
                            onSelected.invoke(index)
                        }
                        .background(
                            if (text == selectedOption) ColorPrimary else MaterialTheme.colorScheme.surfaceVariant
                        )
                        .padding(horizontal = 30.dp, vertical = 16.dp),
                )
            }
        }
    }
}