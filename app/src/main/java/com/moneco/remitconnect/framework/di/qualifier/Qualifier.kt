package com.moneco.remitconnect.framework.di.qualifier

import javax.inject.Qualifier

/**
 * Qualifier annotation for distinguishing HTTP client dependencies.
 * Use this annotation to differentiate between different OkHttpClient instances.
 *
 * @Retention(AnnotationRetention.BINARY) indicates that the annotation is stored in the compiled class files, but not available at runtime.
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class HttpClient

/**
 * Qualifier annotation for distinguishing API dependencies.
 * Use this annotation to differentiate between different Retrofit instances or API-related dependencies.
 *
 * @Retention(AnnotationRetention.BINARY) indicates that the annotation is stored in the compiled class files, but not available at runtime.
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Api

/**
 * Qualifier annotation for distinguishing database name dependencies.
 * Use this annotation to differentiate between different database name string values.
 *
 * @Retention(AnnotationRetention.RUNTIME) indicates that the annotation is stored in the compiled class files and available at runtime.
 */
@Qualifier
@Retention
annotation class DbName