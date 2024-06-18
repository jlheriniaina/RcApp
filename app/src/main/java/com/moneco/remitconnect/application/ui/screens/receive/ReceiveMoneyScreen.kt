package com.moneco.remitconnect.application.ui.screens.receive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Contact
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.ui.components.TabsCustomComponent
import com.moneco.remitconnect.application.ui.components.TopBarLeadingButton
import com.moneco.remitconnect.application.ui.screens.receive.component.NewRecipientContent
import com.moneco.remitconnect.application.ui.screens.receive.component.PreviousRecipientContent
import com.moneco.remitconnect.application.ui.screens.receive.state.ReceiveMoneyAction
import com.moneco.remitconnect.application.ui.screens.receive.state.ReceiveTransactionState

@Composable
fun ReceiveMoneyScreen(state: ReceiveTransactionState,
                       countries: List<Country>,
                       contacts : Map<String, List<Contact>>,
                       actions: ReceiveMoneyAction) {
    Scaffold(
        topBar = {
            TopBarLeadingButton {
                actions.onBack()
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.who_are_you_sending_to),
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 36.sp,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                TabsContent(state, countries,contacts,actions)

            }
        },
        containerColor = Color.White
    )
}

@Composable
fun TabsContent(state: ReceiveTransactionState, countries: List<Country>, contacts : Map<String, List<Contact>>, actions : ReceiveMoneyAction) {
    var tabSelected by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(id = R.string.previous_recipients),
        stringResource(id =  R.string.new_recipient)
    )
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            // Custom tabs component
            TabsCustomComponent(tabs, Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp), onSelected = { optionSelected ->
                tabSelected = optionSelected
            })
        }
        when (tabSelected) {
            0 -> {
                PreviousRecipientContent(state, actions)
            }
            1 -> {
                NewRecipientContent(countries = countries, contacts = contacts,onContinue = actions.onValidate)
            }
        }
    }
}