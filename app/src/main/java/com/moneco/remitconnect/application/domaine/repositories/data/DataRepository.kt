package com.moneco.remitconnect.application.domaine.repositories.data

import com.moneco.remitconnect.application.domaine.entites.Account
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.Wallets

interface DataRepository {
    suspend fun getWallets() : List<Wallets>

    suspend fun getAccounts() : List<Account>

    suspend fun getCountries() : List<Country>


}