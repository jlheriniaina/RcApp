package com.moneco.remitconnect.application.domaine.repositories.transaction

import com.moneco.remitconnect.application.domaine.entites.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun findByUser(id : Long) : Flow<List<Transaction>>

    suspend fun save(transaction: Transaction) : Boolean

    suspend fun find(id : Long, term : String) : List<Transaction>
}