package com.moneco.remitconnect.framework.di.module

import android.content.Context
import com.moneco.remitconnect.RCApp
import com.moneco.remitconnect.application.domaine.repositories.contacts.ContactRepository
import com.moneco.remitconnect.application.domaine.repositories.contacts.ContactRepositoryImpl
import com.moneco.remitconnect.application.domaine.repositories.data.DataRepository
import com.moneco.remitconnect.application.domaine.repositories.data.DataRepositoryImpl
import com.moneco.remitconnect.application.domaine.repositories.transaction.TransactionRepository
import com.moneco.remitconnect.application.domaine.repositories.transaction.TransactionRepositoryImpl
import com.moneco.remitconnect.application.domaine.repositories.user.UserRepository
import com.moneco.remitconnect.application.domaine.repositories.user.UserRepositoryImpl
import com.moneco.remitconnect.application.navigation.AppNavigator
import com.moneco.remitconnect.application.navigation.AppNavigatorImpl
import com.moneco.remitconnect.framework.dataSource.remote.service.RemoteApiService
import com.moneco.remitconnect.framework.dataSource.remote.service.RemoteApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * AppModule Hilt module that provides and dependencies for the application.
 */
@Module
@InstallIn(SingletonComponent::class)

class AppModule {
    /**
     * Injects the application context into the application instance.
     *
     * @param context
     * @return
     */
    @Provides
    @Singleton
    fun provideApplicationInstance(@ApplicationContext context: Context): RCApp {
        return context as RCApp
    }

    /**
     * Injects the appNavigator context into the application instance.
     *
     * @param appNavigatorImpl
     * @return
     */
    @Singleton
    @Provides
    fun provideAppNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator = appNavigatorImpl

    /**
     * Injects the remote api service into the application instance.
     *
     * @param impl
     * @return
     */
    @Singleton
    @Provides
    fun providesRemoteApiService(impl : RemoteApiServiceImpl) : RemoteApiService = impl

    /**
     * Injects the user repository into the application instance.
     *
     * @param impl
     * @return
     */
    @Singleton
    @Provides
    fun provideUserRepository(impl : UserRepositoryImpl): UserRepository = impl

    /**
     * Injects the transaction repository into the application instance.
     *
     * @param impl
     * @return
     */
    @Singleton
    @Provides
    fun provideTransactionRepository(impl : TransactionRepositoryImpl): TransactionRepository = impl

    /**
     * Injects the data repository into the application instance.
     *
     * @param impl
     * @return
     */
    @Singleton
    @Provides
    fun providesDataRepository(impl : DataRepositoryImpl): DataRepository = impl

    /**
     * Injects the contact repository into the application instance.
     *
     * @param impl
     * @return
     */
    @Singleton
    @Provides
    fun providesContactRepository(impl : ContactRepositoryImpl): ContactRepository = impl


}