package com.moneco.remitconnect.helpers

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


/**
 * Formats a Double value as currency in Euros (EUR) using the French locale.
 *
 * @receiver The Double value to format as currency.
 * @return The formatted currency amount as a String.
 */
fun Double.currency() : String {
    // Create a currency formatter for Euros (EUR) with the French locale
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.FRANCE)
    currencyFormat.maximumFractionDigits = 0
    currencyFormat.currency = Currency.getInstance("EUR")
    return currencyFormat.format(this)
}

/**
 * Formats a Double value as currency in West African CFA franc (XOF) using the French locale specific to XOF.
 * Converts the amount from Euros to XOF using a fixed conversion rate.
 *
 * @receiver The Double value to format and convert to XOF.
 * @return The formatted amount in XOF as a String.
 */
fun Double.formatAmountToXOF(): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale("fr", "XOF"))
    currencyFormat.maximumFractionDigits = 0
    currencyFormat.currency = Currency.getInstance("XOF")
    return currencyFormat.format(euroToXof(this))
}
/**
 * Converts an amount from Euros to West African CFA franc (XOF) using a fixed conversion rate.
 *
 * @param amount The amount in Euros to convert to XOF.
 * @return The converted amount in XOF.
 */
fun euroToXof(amount: Double): Double {
    return amount.times(655.94)
}