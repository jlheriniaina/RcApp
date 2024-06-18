package com.moneco.remitconnect.application.domaine.useCase.user

import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.application.domaine.repositories.user.UserRepository
import com.moneco.remitconnect.application.domaine.useCase.base.BaseUseCase
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UserUseCase
@Inject constructor(
    private val userRepository: UserRepository
)
    : BaseUseCase<User, Boolean>{
    override suspend fun execute(input: User): Boolean {
        return coroutineScope {
            userRepository.addUser(user = input)
            return@coroutineScope true
        }
    }
}