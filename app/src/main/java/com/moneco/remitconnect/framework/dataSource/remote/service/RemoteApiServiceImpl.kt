package com.moneco.remitconnect.framework.dataSource.remote.service

import com.moneco.remitconnect.application.domaine.entites.Account
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.Wallets
import com.moneco.remitconnect.framework.dataSource.remote.api.RemoteApi
import com.moneco.remitconnect.framework.di.qualifier.Api
import java.io.IOException
import javax.inject.Inject

class RemoteApiServiceImpl
@Inject
constructor(@Api private var remoteApi: RemoteApi) : RemoteApiService{

    override suspend fun fetchWallets(): List<Wallets> {
        try {
            val response = remoteApi.getWallets()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                 return body
            } else {
                throw IOException("Not found")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchAccounts(): List<Account> {
        try {
            val response = remoteApi.getAccounts()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return body
            } else {
                throw IOException("Not found")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchCountries(): List<Country> {
        try {
            val response = remoteApi.getCountries()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                return body
            } else {
                throw IOException("Not found")
            }
        } catch (e: Exception) {
            throw e
        }
    }

}