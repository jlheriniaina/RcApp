package com.moneco.remitconnect.helpers

/**
 * A sealed class representing different types of navigation intents.
 * Used to encapsulate navigation actions in a type-safe manner.
 */
sealed class NavigationIntent {

    /**
     * Represents an intent to navigate back in the navigation stack.
     *
     * @property route Optional route to pop back to. If null, pops the current destination.
     * @property inclusive Whether the specified route should also be popped.
     */
    data class NavigateBack(
        val route: String? = null,
        val inclusive: Boolean = false,
    ) : NavigationIntent()


    /**
     * Represents an intent to navigate to a specific route.
     *
     * @property route The route to navigate to.
     * @property popUpToRoute Optional route to pop up to before navigating. If null, no pop-up is performed.
     * @property inclusive Whether the specified pop-up route should also be popped.
     * @property isSingleTop Whether to launch the destination as a single top activity, avoiding multiple instances.
     */
    data class NavigateTo(
        val route: String,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false,
        val isSingleTop: Boolean = false,
    ) : NavigationIntent()
}
