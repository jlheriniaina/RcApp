package com.moneco.remitconnect.application.domaine.useCase.contact

import com.moneco.remitconnect.application.domaine.entites.Contact
import com.moneco.remitconnect.application.domaine.repositories.contacts.ContactRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetContactUseCase
@Inject
constructor(private val repository: ContactRepository) : BaseUseCase<Any, Flow<List<Contact>>> {
    override suspend fun execute(input: Any): Flow<List<Contact>> {
       return flow {
              val contacts = repository.getContacts()
              emit(contacts)
       }.flowOn(Dispatchers.IO)
    }
}