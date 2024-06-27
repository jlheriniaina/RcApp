package com.moneco.remitconnect.application.ui.screens.sending

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.ui.components.TopBarLeadingButton
import com.moneco.remitconnect.application.ui.components.ValidateButton
import com.moneco.remitconnect.application.ui.screens.sending.components.AmountContent
import com.moneco.remitconnect.application.ui.screens.sending.components.FeesContent
import com.moneco.remitconnect.application.ui.screens.sending.components.LineContent
import com.moneco.remitconnect.application.ui.screens.sending.components.SendContentPicker
import com.moneco.remitconnect.application.ui.screens.sending.state.SendingActions
import com.moneco.remitconnect.application.ui.theme.CeruleanBlue
import com.moneco.remitconnect.application.ui.theme.DuskGray
import com.moneco.remitconnect.application.ui.theme.LightGray
import com.moneco.remitconnect.application.ui.theme.midnightBlue
import com.moneco.remitconnect.application.ui.theme.outfitSansFamily
import com.moneco.remitconnect.helpers.currency

@Composable
fun SendingScreen(user: User?, transaction: Transaction, actions : SendingActions) {
    var amount by rememberSaveable { mutableStateOf("") }
    var isValid by rememberSaveable { mutableStateOf(true) }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
           TopBarLeadingButton {
                  actions.onBack()
           }
        },
        content = { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = stringResource(R.string.send_money),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = outfitSansFamily,
                        color = Color(0xFF00122C),
                        lineHeight = 36.sp,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "How much are you sending?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = outfitSansFamily,
                        lineHeight = 36.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(327.dp, 97.dp)
                            .border(
                                1.dp,
                                color = if (isValid) LightGray else
                                    MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                val maxChar = 5
                                TextField(
                                    value = amount,
                                    onValueChange = {
                                        if (it.length <= maxChar) amount = it
                                        isValid = it.isEmpty()
                                    },
                                    colors = TextFieldDefaults.colors(
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                    ),
                                    textStyle = TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.W600,
                                        color = midnightBlue
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "0.0",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.W600,
                                                color = midnightBlue,
                                            ),
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number
                                    ),
                                    singleLine = true,
                                    maxLines = 1,
                                    keyboardActions = KeyboardActions(
                                        onNext = KeyboardActions.Default.onNext
                                    )
                                )
                                Text(
                                    text = "EUR",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = outfitSansFamily,
                                    color = DuskGray
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(
                                        if (!isValid) MaterialTheme.colorScheme.surfaceVariant
                                        else LightGray
                                    )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        .padding(start = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Your current balance is ${(user?.balance ?: 0.0).currency()}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        fontFamily = outfitSansFamily,
                                        color = if (!isValid)
                                            MaterialTheme.colorScheme.primary
                                        else midnightBlue
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    )
                    {
                        Text(
                            text = stringResource(R.string.yearly_free_remittances),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = outfitSansFamily,
                            lineHeight = 36.sp,
                        )
                        Text(
                            text = stringResource(R.string.remittances_are_free_with_moneco),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = outfitSansFamily,
                            color = DuskGray
                        )
                        Text(
                            modifier = Modifier.clickable {},
                            text = stringResource(R.string.check_number_of_free_remittance_remaining),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = outfitSansFamily,
                            color = CeruleanBlue
                        )
                        Spacer(modifier = Modifier.height(28.dp))
                        Text(
                            text = stringResource(R.string.fees_breakdown),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = outfitSansFamily,
                            lineHeight = 36.sp,
                        )
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom),
                            modifier = Modifier.padding(bottom = 24.dp)
                        ) {

                            FeesContent()
                            LineContent()
                            AmountContent(amount = amount.toDoubleOrNull()?: 0.0)
                            Spacer(modifier = Modifier.weight(1f))

                        }
                    }
                }
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .shadow(
                        16.dp, RoundedCornerShape(
                            topStart = 2.dp, topEnd = 2.dp,
                        )
                    )
                    .background(
                        Color.White,
                        RoundedCornerShape(
                            topStart = 16.dp, topEnd = 16.dp,

                            )
                    ),

            ) {
                ValidateButton(title = stringResource(id = R.string.send),
                    isEnable = !isValid){
                    transaction.amount = amount.toDoubleOrNull()?: 0.0
                    showBottomSheet = true
                }
            }
        }
    )

    if (showBottomSheet){
        SendContentPicker(
            transaction = transaction
        , onDismiss = {
            showBottomSheet = false
        }){
            showBottomSheet = false
            actions.onSend(it)
        }
    }
}