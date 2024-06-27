package com.moneco.remitconnect.application.domaine.useCase.transaction

import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.domaine.repositories.transaction.TransactionRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTransactionsUseCase
@Inject
constructor(private val transactionRepository: TransactionRepository) : BaseUseCase<Long, Flow<List<Transaction>>>{
    override suspend fun execute(input: Long): Flow<List<Transaction>> {
       return transactionRepository.findByUser(input)
    }
}