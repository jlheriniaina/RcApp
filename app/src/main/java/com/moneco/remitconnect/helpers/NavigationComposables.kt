package com.moneco.remitconnect.helpers

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

/**
 * Custom NavHost function for setting up navigation.
 *
 * @param navController The NavHostController to manage navigation.
 * @param startDestination The starting destination of the navigation graph.
 * @param modifier Modifier to be applied to the NavHost.
 * @param route Optional route for the NavHost.
 * @param builder Lambda function for building the navigation graph.
 */
@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit
) {
    // Set up the NavHost with the provided parameters
    NavHost(
        navController = navController,
        startDestination = startDestination.fullRoute,
        modifier = modifier,
        route = route,
        builder = builder
    )
}

/**
 * Custom composable function for adding destinations to the navigation graph with animations.
 *
 * @param destination The destination to be added to the navigation graph.
 * @param arguments Optional list of navigation arguments.
 * @param deepLinks Optional list of deep links.
 * @param content Composable function that defines the UI for the destination.
 */
@ExperimentalAnimationApi
fun NavGraphBuilder.composable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit) {
    // Add a composable destination to the navigation graph with custom animations
    composable(
        route = destination.fullRoute,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            // Define the enter transition animation
            fadeIn(
                animationSpec = tween(300, easing = FastOutLinearInEasing)
            )
        },
        exitTransition = {
            // Define the exit transition animation
            fadeOut(
                animationSpec = tween(300, easing = LinearOutSlowInEasing)
            )
        },
        content = content
    )
}