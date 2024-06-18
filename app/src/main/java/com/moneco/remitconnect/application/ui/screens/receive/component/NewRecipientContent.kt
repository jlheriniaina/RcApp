package com.moneco.remitconnect.application.ui.screens.receive.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Contact
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.defaultCountry
import com.moneco.remitconnect.application.ui.components.ContactButton
import com.moneco.remitconnect.application.ui.components.CustomStyleOutlinedTextField
import com.moneco.remitconnect.application.ui.components.ValidateButton
import com.moneco.remitconnect.application.ui.theme.DuskGray
import com.moneco.remitconnect.application.ui.theme.midnightBlue
import com.moneco.remitconnect.helpers.toast

@Composable
fun NewRecipientContent(countries : List<Country>, contacts : Map<String, List<Contact>>, onContinue: (String, String, String, String) -> Unit){
    var phone by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var country : Country by remember { mutableStateOf(defaultCountry) }

    var showContact by remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (content, footer) = createRefs()
        Column(
            modifier = Modifier.constrainAs(content){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.country),
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                lineHeight = 21.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            CountrySelectorContent(data = countries){ item ->
                country = item
            }
            Spacer(modifier = Modifier.height(16.dp))
            ContactButton{
                showContact = true
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = stringResource(R.string.or_add_manually),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    lineHeight = 18.sp,
                    color = DuskGray
                )
                HorizontalDivider(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.phone_number),
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 24.sp,
                color = midnightBlue
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomStyleOutlinedTextField(
                text = phone,
                placeHolder = stringResource(R.string.hint_phone),
                keyboardType = KeyboardType.Phone,
                onTextChange = {
                    phone = it
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.first_name),
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 24.sp,
                color = midnightBlue
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            CustomStyleOutlinedTextField(
                text = name,
                placeHolder = stringResource(R.string.hint_name),
                onTextChange = {
                    name = it
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.last_name),
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                lineHeight = 24.sp,
                color = midnightBlue
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomStyleOutlinedTextField(
                text = lastname,
                placeHolder = stringResource(R.string.hint_lastname),
                onTextChange = {
                    lastname = it
                }
            )
            Spacer(modifier = Modifier.height(130.dp))
        }
        Box(
            modifier = Modifier.constrainAs(footer) {
                bottom.linkTo(parent.bottom, margin = -24.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                    16.dp, RoundedCornerShape(
                        topStart = 16.dp, topEnd = 16.dp
                    )
                )
                .background(
                     Color.White,
                     RoundedCornerShape(
                         topStart = 16.dp, topEnd = 16.dp,
                    )
                ),
        ) {
            ValidateButton(
                title = stringResource(id = R.string.continue_txt),
                isEnable = phone.isNotEmpty() &&
                        name.isNotEmpty() &&
                        lastname.isNotEmpty()){
                onContinue(phone,name,lastname, country.currencyCode)
            }
        }
    }

    if (showContact){
        if (contacts.isNotEmpty()){
            ContactSelectorPicker(items = contacts, onSelected = {
                phone = it.phone ?: ""
                name = it.name
                lastname = it.name
                showContact = false
            }) {
                showContact = false
            }
        }else {
            LocalContext.current.toast(stringResource(R.string.not_contact_found))
        }

    }

}