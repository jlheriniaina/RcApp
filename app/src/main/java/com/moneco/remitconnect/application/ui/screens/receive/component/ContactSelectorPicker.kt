package com.moneco.remitconnect.application.ui.screens.receive.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.moneco.remitconnect.application.domaine.entites.Contact
import java.util.Collections

@Composable
fun ContactSelectorPicker(items : Map<String, List<Contact>> = Collections.emptyMap(),
                          onSelected : (Contact) -> Unit,
                          onDismiss : () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(),
            shape = RoundedCornerShape(16.dp)
        ) {
            ContactsList(items){
                onSelected(it)
            }
        }
    }
}