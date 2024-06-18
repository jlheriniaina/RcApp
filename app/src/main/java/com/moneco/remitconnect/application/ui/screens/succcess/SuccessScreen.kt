package com.moneco.remitconnect.application.ui.screens.succcess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.MainViewModel


@Composable
fun SuccessScreen(viewModel: MainViewModel) {

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(start = 40.dp, end = 40.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_success),
                    contentDescription = stringResource(R.string.success_transaction_illustration_desc)
                )
                Text(
                    text = stringResource(R.string.your_money_is_on_the_way),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick ={
                        viewModel.navigationHome()
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(311.dp, 56.dp),
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.primary
                    ),

                ) {
                    Text(
                        text = stringResource(id = R.string.got_it),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp,
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    )
}