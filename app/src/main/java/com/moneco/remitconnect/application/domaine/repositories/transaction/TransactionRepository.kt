package com.moneco.remitconnect.application.domaine.repositories.transaction

import com.moneco.remitconnect.application.domaine.entites.Transaction

interface TransactionRepository {
    suspend fun findByUser(id : Long) : List<Transaction>

    suspend fun save(transaction: Transaction) : Boolean

    suspend fun find(id : Long, term : String) : List<Transaction>
}