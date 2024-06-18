package com.moneco.remitconnect.application.domaine.repositories.user

import com.moneco.remitconnect.application.domaine.entites.User

interface UserRepository {
    suspend fun addUser(user : User)

    suspend fun getUser(id : Long) : User
}