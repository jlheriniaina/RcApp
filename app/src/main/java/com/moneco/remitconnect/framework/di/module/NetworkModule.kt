package com.moneco.remitconnect.framework.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moneco.remitconnect.framework.dataSource.remote.api.ApiEndPoint
import com.moneco.remitconnect.framework.dataSource.remote.api.RemoteApi
import com.moneco.remitconnect.framework.di.qualifier.Api
import com.moneco.remitconnect.framework.di.qualifier.HttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val TIMEOUT = 460L

    /**
     * Provides the Gson instance for JSON serialization and deserialization.
     * Configures the Gson instance with specific date format, field naming policy, and leniency.
     *
     * @return A configured Gson instance.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("yyyy-MM-dd")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
        return gsonBuilder.create()
    }
    /**
     * Provides the RemoteApi instance for making API calls.
     * Configures Retrofit with a base URL, a custom OkHttpClient, and a Gson converter.
     *
     * @param client The OkHttpClient instance to use for network requests.
     * @param gson The Gson instance for JSON conversion.
     * @return A configured RemoteApi instance.
     */
    @Provides
    @Singleton
    @Api
    internal fun provideRemote(
        @HttpClient client: OkHttpClient, gson : Gson
    ): RemoteApi {

        return Retrofit.Builder()
                    .client(client)
                    .baseUrl(ApiEndPoint.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(RemoteApi::class.java)
    }
    /**
     * Provides the OkHttpClient instance for handling HTTP requests.
     * Configures OkHttpClient with logging interceptor and timeout settings.
     *
     * @param httpLoggingInterceptor The HttpLoggingInterceptor for logging HTTP request and response data.
     * @return A configured OkHttpClient instance.
     */
    @Provides
    @Singleton
    @HttpClient
    internal fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
                  .addInterceptor(httpLoggingInterceptor)
                  .callTimeout(TIMEOUT, TimeUnit.SECONDS)
                  .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                  .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                  .build()
    }
    /**
     * Provides the HttpLoggingInterceptor instance for logging HTTP request and response data.
     * Sets the logging level to BODY to log the request and response lines and their respective headers and bodies (if present).
     *
     * @return A configured HttpLoggingInterceptor instance.
     */
    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}