package com.moneco.remitconnect.framework.dataSource.remote.api

import com.moneco.remitconnect.application.domaine.entites.Account
import com.moneco.remitconnect.application.domaine.entites.Country
import com.moneco.remitconnect.application.domaine.entites.Wallets
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApi {

    /**
     * get all accounts
     *
     * @return Response<List<Account>>
     */
    @GET(ApiEndPoint.ACCOUNT)
    suspend fun getAccounts() : Response<List<Account>>

    /**
     * get all wallets
     *
     * @return Response<List<Wallets>>
     */
    @GET(ApiEndPoint.WALLETS)
    suspend fun getWallets() : Response<List<Wallets>>

    /**
     * get all countries
     *
     * @return Response<List<Country>>
     */
    @GET(ApiEndPoint.COUNTRY)
    suspend fun getCountries() : Response<List<Country>>
}