package com.moneco.remitconnect.framework.dataSource.remote.service

import com.moneco.remitconnect.application.domaine.entites.Account
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.Wallets

interface RemoteApiService {

    /**
     * Fetch all the wallets
     * @return list of wallets
     */
    suspend fun fetchWallets(): List<Wallets>

    /**
     * Fetch all the accounts
     * @return list of accounts
     */
    suspend fun fetchAccounts() : List<Account>

    /**
     * Fetch all the countries
     *
     * @return list of countries
     */
    suspend fun fetchCountries() : List<Country>
}