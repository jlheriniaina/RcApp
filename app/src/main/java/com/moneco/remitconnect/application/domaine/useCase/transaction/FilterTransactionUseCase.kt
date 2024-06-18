package com.moneco.remitconnect.application.domaine.useCase.transaction

import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.domaine.repositories.transaction.TransactionRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import com.moneco.remitconnect.application.ui.screens.receive.state.QueryTransaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilterTransactionUseCase
@Inject
constructor(private val transactionRepository: TransactionRepository) :
    BaseUseCase<QueryTransaction, Flow<List<Transaction>>> {
    override suspend fun execute(input: QueryTransaction): Flow<List<Transaction>> {
        return flow {
                val transactionList = transactionRepository.find(input.id, input.term)
                emit(transactionList)
        }
    }
}