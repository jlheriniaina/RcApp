package com.moneco.remitconnect.framework.di.module

import android.content.Context
import androidx.room.Room
import com.moneco.remitconnect.framework.dataSource.locals.database.AppDatabase
import com.moneco.remitconnect.framework.dataSource.locals.database.dao.TransactionDao
import com.moneco.remitconnect.framework.dataSource.locals.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database hilt module  that provides db and dependencies for the application.
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    /**
     * Database name
     */
    private val DATABESE_NAME  = "remit_connect_db"

    /**
     * Provides the database instance for dependency injection.
     * This function uses Dagger Hilt to manage the lifecycle and dependencies of the database.
     *
     * @param context The application context, provided by Hilt.
     * @return The singleton instance of the AppDatabase.
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABESE_NAME)
            .fallbackToDestructiveMigration(false).build()


    /**
     * Provides the UserDao instance for dependency injection.
     * This function uses Dagger Hilt to provide a singleton instance of UserDao.
     *
     * @param appDatabase The database instance from which the UserDao is retrieved.
     * @return The singleton instance of UserDao.
     */
    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase) : UserDao = appDatabase.userDao()

    /**
     * Provides the TransactionDao instance for dependency injection.
     * This function uses Dagger Hilt to provide a singleton instance of TransactionDao.
     *
     * @param appDatabase The database instance from which the TransactionDao is retrieved.
     * @return The singleton instance of TransactionDao.
     */
    @Singleton
    @Provides
    fun providesTransactionDao(appDatabase: AppDatabase) : TransactionDao = appDatabase.transactionDao()

}