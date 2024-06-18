package com.moneco.remitconnect.application.domaine.useCase.wallets

import com.moneco.remitconnect.application.domaine.entites.Wallets
import com.moneco.remitconnect.application.domaine.repositories.data.DataRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetWalletsUseCase
@Inject constructor(private val repository: DataRepository)
: BaseUseCase<Any, Flow<List<Wallets>>> {
    override suspend fun execute(input: Any): Flow<List<Wallets>> {
        return flow {
            val data = repository.getWallets()
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}