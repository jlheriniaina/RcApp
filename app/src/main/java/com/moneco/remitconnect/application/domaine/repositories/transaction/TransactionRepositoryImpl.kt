package com.moneco.remitconnect.application.domaine.repositories.transaction

import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.framework.dataSource.locals.database.dao.TransactionDao
import javax.inject.Inject

class TransactionRepositoryImpl
@Inject
constructor(private val transactionDao: TransactionDao) : TransactionRepository{
    override suspend fun findByUser(id: Long): List<Transaction> {
       return transactionDao.find(id)
    }

    override suspend fun save(transaction: Transaction): Boolean {
       val id = transactionDao.insert(transaction)
       return id > 0
    }

    override suspend fun find(id: Long, term: String): List<Transaction> {
        return transactionDao.find(id, term)
    }
}