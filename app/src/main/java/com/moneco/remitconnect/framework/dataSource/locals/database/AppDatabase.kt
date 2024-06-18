package com.moneco.remitconnect.framework.dataSource.locals.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moneco.remitconnect.application.domaine.entites.Transaction
import com.moneco.remitconnect.application.domaine.entites.User
import com.moneco.remitconnect.framework.dataSource.locals.database.dao.TransactionDao
import com.moneco.remitconnect.framework.dataSource.locals.database.dao.UserDao

/**
 * Room database class for the Remit Connect app.
 */
@Database(entities = [User::class, Transaction::class], version = 2 ,exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Abstract method to get the UserDao.
     * UserDao provides access to the User table in the database.
     *
     * @return An instance of UserDao to interact with User data.
     */
    abstract fun userDao() : UserDao

    /**
     * Abstract method to get the TransactionDao.
     * TransactionDao provides access to the Transaction table in the database.
     *
     * @return An instance of TransactionDao to interact with Transaction data.
     */
    abstract fun transactionDao() : TransactionDao


}
