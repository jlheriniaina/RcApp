package com.moneco.remitconnect.application.domaine.useCase.transaction

import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.domaine.repositories.transaction.TransactionRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import javax.inject.Inject


class CreateTransactionUseCase
@Inject constructor(private val transactionRepository: TransactionRepository) : BaseUseCase<Transaction, Boolean>{
    override suspend fun execute(input: Transaction): Boolean {
        return transactionRepository.save(input)
    }
}