package com.moneco.remitconnect.application.domaine.useCase.country

import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.repositories.data.DataRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCountryUseCase
@Inject
constructor(private val repository: DataRepository) : BaseUseCase<Any,Flow<List<Country>>>{
    override suspend fun execute(input: Any): Flow<List<Country>> {
       return flow {
           val response = repository.getCountries()
           emit(response)
       }.flowOn(Dispatchers.IO)
    }
}