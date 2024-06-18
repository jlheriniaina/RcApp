package com.moneco.remitconnect.application.ui.screens.receive.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.defaultCountry
import com.moneco.remitconnect.application.ui.theme.DuskGray
import com.moneco.remitconnect.application.ui.theme.LightGray
import com.moneco.remitconnect.helpers.toast


@Composable
fun CountrySelectorContent(data : List<Country>,
                           isOnlyFlagShow: Boolean = false,
                           onSelected : (Country) -> Unit) {
    var selectedCountry by remember { mutableStateOf(when(data.isNotEmpty()){
        true -> data[0]
        else -> defaultCountry
    }) }
    var isOpenDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val message = stringResource(R.string.no_country_found)

    Surface(
        shape = MaterialTheme.shapes.small,
        color = Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = LightGray
        ),
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { isOpenDialog = true }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 16.dp, end = 12.dp)
                .clickable {
                    if (data.isNotEmpty()){
                        isOpenDialog = true
                   }else {
                        context.toast(message)
                    }
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = selectedCountry.getFlags(),
                    ),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp),
                )
                if (!isOnlyFlagShow) {
                    Text(
                        "${selectedCountry.name} (${selectedCountry.getCountryCode()})",
                        Modifier.padding(8.dp),
                        fontSize = 17.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W500,
                        lineHeight = 24.sp
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    selectedCountry.getCountryCode(),
                    color = DuskGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 13.dp)
                )
            }
        }
    }
    // choose country dialog
    if (isOpenDialog) {
        Dialog(
            onDismissRequest = { isOpenDialog = false },
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(),
                shape = RoundedCornerShape(16.dp)
            ) {
                LazyColumn{
                    items(data){ countryItem ->
                        Row(
                            Modifier
                                .padding(
                                    horizontal = 18.dp,
                                    vertical = 18.dp
                                )
                                .clickable {
                                    isOpenDialog = false
                                    selectedCountry = countryItem
                                    onSelected(countryItem)
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(
                                    id = countryItem.getFlags(),
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(24.dp),
                            )
                            Text(
                                "${countryItem.name} (${countryItem.getCountryCode()})",
                                Modifier.padding(horizontal = 18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}