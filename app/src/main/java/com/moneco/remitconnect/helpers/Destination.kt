package com.moneco.remitconnect.helpers

/**
 * Constants defining keys for various parameters used in navigation destinations.
 */
const val PHONE_NUMBER_KEY = "phone"
const val FIRST_NAME_KEY = "first_name"
const val LAST_NAME_KEY = "last_name"
const val COUNTRY_CODE_KEY = "country_code"
const val WALLET_KEY = "wallet_id"
const val USER_ID = "user_id"

/**
 * Sealed class representing different navigation destinations in the application.
 *
 * @param route The base route string for the destination.
 * @param params Optional vararg of parameter names to append to the route.
 */
sealed class Destination(protected val route: String, vararg params: String) {

    /**
     * Computed property that returns the full route string with appended parameters.
     */
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    /**
     * Sealed subclass for navigation destinations that do not take any parameters.
     */
    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }
    /**
     * Singleton object representing the Home screen destination.
     */
    data object HomeScreen : NoArgumentsDestination(NavigationConstant.HOME)

    /**
     * Singleton object representing the Send Money screen destination.
     */
    data object SendMoneyScreen : NoArgumentsDestination(NavigationConstant.SEND_MONEY_OPTION)

    /**
     * Singleton object representing the Send to Africa screen destination.
     */
    data object SendToAfrica : NoArgumentsDestination(NavigationConstant.SEND_TO_AFRICA)

    /**
     * Singleton object representing the Receive Money screen destination.
     */
    data object ReceiveMoneyScreen : NoArgumentsDestination(NavigationConstant.RECEIVE_MONEY)

    /**
     * Destination representing the Wallet screen with specific parameters.
     *
     * @param route The base route string for the destination.
     * @param params Parameters expected for this destination.
     */
    data object WalletScreen : Destination(NavigationConstant.WALLET,
        "phone", "first_name", "last_name",
                "country_code","user_id"
        ){
        operator fun invoke(phone: String, firstName: String, lastName: String,
                            userId: Long,countryCode: String): String = route.appendParams(
            PHONE_NUMBER_KEY to phone,
            FIRST_NAME_KEY to firstName,
            LAST_NAME_KEY to lastName,
            USER_ID to userId,
            COUNTRY_CODE_KEY to countryCode
        )
    }

    /**
     * Singleton object representing the Success screen destination.
     */
    data object SuccessScreen : NoArgumentsDestination(NavigationConstant.SUCCESS_MONEY)

    /**
     * Destination representing the Sending screen with specific parameters.
     *
     * @param route The base route string for the destination.
     * @param params Parameters expected for this destination.
     */
    data object SendingScreen : Destination(NavigationConstant.SENDING_MONEY,
        "phone", "first_name", "last_name",
                 "country_code", "wallet_id"
    ){
        operator fun invoke(phone: String, firstName: String, lastName: String,
                            countryCode: String, walletId : String): String = route.appendParams(
            PHONE_NUMBER_KEY to phone,
            FIRST_NAME_KEY to firstName,
            LAST_NAME_KEY to lastName,
            COUNTRY_CODE_KEY to countryCode,
            WALLET_KEY to walletId
        )
    }

}

/**
 * Extension function to append parameters to a route string.
 *
 * @receiver The base route string to append parameters to.
 * @param params Vararg of parameter key-value pairs to append to the route.
 * @return The route string with appended parameters.
 */
fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)
    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }
    return builder.toString()
}