package com.example.test.common.data.di

import com.example.test.common.data.interceptor.RequestInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Singleton
    @Provides
    fun provideConvertJson(json: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message: String? -> Timber.tag("network").i("LOG_BASIC $message") }
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

    @Singleton
    @Provides
    fun provideRequestInterceptor() = RequestInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        requestInterceptor: RequestInterceptor,
        httpLogInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(httpLogInterceptor)
            .build()

    @Singleton
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        kotlinConverterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("")
            .client(okHttpClient)
            .addConverterFactory(kotlinConverterFactory)
            .build()
}