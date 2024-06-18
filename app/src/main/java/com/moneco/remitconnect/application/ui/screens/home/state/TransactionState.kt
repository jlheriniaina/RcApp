package com.moneco.remitconnect.application.ui.screens.home.state

import com.moneco.remitconnect.application.domaine.entites.Transaction

/**
 * TransactionState is a sealed class representing the various states of transaction data in the HomeScreen.
 */
sealed class TransactionState {
    /**
     * Represents the idle state when no action has been taken yet.
     */
    data object Idle : TransactionState()

    /**
     * Represents the loading state when transaction data is being fetched.
     */
    data object Loading : TransactionState()
    /**
     * Represents the success state when transaction data has been successfully fetched.
     *
     * @property items The list of transactions that have been fetched.
     */
    data class Success(val items : List<Transaction>) : TransactionState()

    /**
     * Represents the error state when there is an issue fetching transaction data.
     *
     * @property message The resource ID of the error message to be displayed.
     */
    data class Error(val message : Int)  : TransactionState()
}