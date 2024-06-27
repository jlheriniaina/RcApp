package com.moneco.remitconnect.application.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moneco.remitconnect.R
import com.moneco.remitconnect.application.ui.theme.ColorPrimary
import com.moneco.remitconnect.application.ui.theme.PaperYellow
import com.moneco.remitconnect.application.ui.theme.SlateBlue


sealed class RouteItem(var icon: Int, var title: String) {
    data object Home : RouteItem(R.drawable.ic_home, "Home")
    data object Cards : RouteItem(R.drawable.ic_cards, "Cards")
    data object Send : RouteItem(R.drawable.ic_send, "Send")
    data object Tontines : RouteItem(R.drawable.ic_coin, "Tontines")
    data object Settings : RouteItem( R.drawable.ic_settings, "Settings")
}
@Composable
fun NavigationBarContent(onItemClick: () -> Unit) {
    val items = listOf(RouteItem.Home, RouteItem.Cards, RouteItem.Send, RouteItem.Tontines, RouteItem.Settings)
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        contentColor = Color.White,
        tonalElevation = NavigationBarDefaults.Elevation,
        containerColor = Color.White
    ){
        items.forEachIndexed { index, item ->
            val isSendButton = index == 2
            NavigationBarItem(
                selected = index == 0,
                onClick = {
                     if (isSendButton) {
                         onItemClick()
                     }
                },
                icon = {
                    if (isSendButton) {
                        Box(
                            modifier = Modifier
                                .offset(y = 8.dp)
                                .size(56.dp)
                                .background(
                                    color = PaperYellow,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = item.icon),
                                contentDescription = item.title,
                                tint = SlateBlue
                            )
                        }
                    } else {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                        )
                    }
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                    )
                },
                label = {
                    if (!isSendButton) Text(text = item.title) else Text(text = "")
                },
            )
        }
    }
}

@Composable
fun RowScope.NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ColorPrimary,
            unselectedIconColor = Color.Gray,
            selectedTextColor = ColorPrimary,
            unselectedTextColor = Color.Black,
            indicatorColor = Color.White
        ),
    )
}
