package com.moneco.remitconnect.application.domaine.useCase.user

import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.domaine.repositories.user.UserRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase
@Inject constructor(private val userRepository: UserRepository) : BaseUseCase<Long, Flow<User>>{
    override suspend fun execute(input: Long): Flow<User> {
        return flow {
               val user = userRepository.getUser(input)
               emit(user)
        }
    }
}