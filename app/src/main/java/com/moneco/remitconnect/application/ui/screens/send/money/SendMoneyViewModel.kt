package com.moneco.remitconnect.application.ui.screens.send.money

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.navigation.AppNavigator
import com.moneco.remitconnect.helpers.Destination
import com.moneco.remitconnect.helpers.NavigationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel
@Inject constructor(private val appNavigator: AppNavigator) : ViewModel() {
    val items = listOf(SendingOption("To Moneco balance", R.drawable.ic_moneco_balance, route = Route.MONECO),
                       SendingOption("Bank transfer",R.drawable.ic_bank_transfer, route = Route.BANK_TRANSFER),
                       SendingOption("Send to Africa", R.drawable.ic_send_to_africa, route = Route.AFRICA_SEND))

    val itemsTypes = listOf(
        SendingOption("Mobile wallets", R.drawable.ic_arrow_square_right, route = Route.MOBILE_WALLETS),
        SendingOption("Bank transfer",R.drawable.ic_arrow_square_right, Route.BANK_TRANSFER_WALLETS)
    )

    fun navigateTo(item : SendingOption) = viewModelScope.launch {
           when(item.route) {
               Route.MONECO,Route.BANK_TRANSFER_WALLETS, Route.BANK_TRANSFER -> {}
               Route.AFRICA_SEND -> appNavigator.navigateTo(Destination.SendToAfrica(), NavigationConstant.SEND_MONEY_OPTION)
               Route.MOBILE_WALLETS -> appNavigator.navigateTo(Destination.ReceiveMoneyScreen(), NavigationConstant.SEND_MONEY_OPTION)
           }
    }

    fun navigateBack() = viewModelScope.launch {
        appNavigator.navigateBack()
    }


}
data class SendingOption(val title : String, val icon : Int,val route : Route)

enum class Route {
    MONECO,BANK_TRANSFER,AFRICA_SEND,MOBILE_WALLETS,BANK_TRANSFER_WALLETS
}