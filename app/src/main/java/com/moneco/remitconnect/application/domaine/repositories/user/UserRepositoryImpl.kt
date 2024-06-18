package com.moneco.remitconnect.application.domaine.repositories.user

import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.framework.dataSource.locals.database.dao.UserDao
import javax.inject.Inject

class UserRepositoryImpl
@Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun addUser(user: User) {
        userDao.insert(user = user)
    }

    override suspend fun getUser(id: Long): User {
        return userDao.find(id)
    }
}