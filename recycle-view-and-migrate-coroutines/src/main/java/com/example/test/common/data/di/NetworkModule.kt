package com.example.test.common.data.di

import com.example.test.common.application.di.ApplicationScope
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

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @ApplicationScope
    @Provides
    fun provideConvertJson(json: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @ApplicationScope
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message: String? -> Timber.tag("network").i("LOG_BASIC $message") }
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

    @ApplicationScope
    @Provides
    fun provideRequestInterceptor() = RequestInterceptor()

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(
        requestInterceptor: RequestInterceptor,
        httpLogInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(httpLogInterceptor)
            .build()

    @ApplicationScope
    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        kotlinConverterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            //Так как домен не был дан, оставил stub
            .baseUrl("http://test/v1/")
            .client(okHttpClient)
            .addConverterFactory(kotlinConverterFactory)
            .build()
}