package com.moneco.remitconnect.application.domaine.repositories.data

import com.moneco.remitconnect.application.domaine.entites.Account
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.Wallets
import com.moneco.remitconnect.framework.dataSource.remote.service.RemoteApiService
import javax.inject.Inject

class DataRepositoryImpl @Inject
constructor(private val remoteApiService: RemoteApiService) : DataRepository {
    override suspend fun getWallets(): List<Wallets> {
        return remoteApiService.fetchWallets()
    }

    override suspend fun getAccounts(): List<Account> {
        return remoteApiService.fetchAccounts()
    }

    override suspend fun getCountries(): List<Country> {
        return remoteApiService.fetchCountries()
    }
}