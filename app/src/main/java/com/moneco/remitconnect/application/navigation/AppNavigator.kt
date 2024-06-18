package com.moneco.remitconnect.application.navigation

import com.moneco.remitconnect.helpers.NavigationIntent
import kotlinx.coroutines.channels.Channel

interface AppNavigator {
    val navigationChannel: Channel<NavigationIntent>

    /**
     *
     *
     * @param route
     * @param inclusive
     */
    suspend fun navigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    )

    /**
     *
     *
     * @param route
     * @param inclusive
     */
    fun tryNavigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    )

    /**
     *
     *
     * @param route
     * @param popUpToRoute
     * @param inclusive
     * @param isSingleTop
     */
    suspend fun navigateTo(
        route: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    )

    /**
     *
     *
     * @param route
     * @param popUpToRoute
     * @param inclusive
     * @param isSingleTop
     */
    fun tryNavigateTo(
        route: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    )
}