package com.moneco.remitconnect.application.ui.screens.receive.state

import com.moneco.remitconnect.application.domaine.entites.Transaction

sealed class ReceiveTransactionState {
   data object Idle : ReceiveTransactionState()
   data object Loading : ReceiveTransactionState()
   data object Empty : ReceiveTransactionState()
   data class Success(val items : List<Transaction>) : ReceiveTransactionState()

}

data class QueryTransaction(
   val id : Long,
   val term : String
)