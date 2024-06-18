package com.moneco.remitconnect.application.ui.screens.receive.component

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.moneco.remitconnect.application.domaine.entites.Contact
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneco.remitconnect.application.ui.theme.contactColors

import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactsList(
    contacts: Map<String, List<Contact>> = Collections.emptyMap(),
    onSelected: (Contact) -> Unit = {}
){
    LazyColumn(Modifier.fillMaxWidth()) {
        contacts.map { entry ->
            stickyHeader {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, bottom = 5.dp, top = 5.dp))
                {
                    Text(
                        text = entry.key,
                        modifier = Modifier,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            items(
                items = entry.value,
                key = { c -> c.id }
            ){ contact ->
                // remember the color derived by the hash of the input string
                val color = rememberContactColor(contactId = contact.id)
                ContactListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelected(contact) }
                        .padding(start = 40.dp, top = 5.dp, end = 5.dp, bottom = 5.dp),
                    contact = contact,
                    color = color,
                    initial = entry.key
                )
            }
        }
    }
}

@Composable
fun ContactListItem(
    modifier: Modifier = Modifier,
    contact: Contact,
    initial: String,
    color: Color = Color.Red,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactInitial(
            color = color,
            initial = initial,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = contact.name
        )
    }
}

@Composable
fun ContactInitial(
    modifier: Modifier = Modifier,
    color: Color,
    initial: String,
    circleSize: Dp = 40.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
) {
    Box(modifier.size(circleSize), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(color))
        }
        Text(text = initial, style = textStyle, color = Color.White)
    }
}

@Composable
fun rememberContactColor(contactId: String) = remember(contactId){
    contactColors[contactId.hashCode() % contactColors.size]
}


