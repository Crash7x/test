package com.example.test.screens.confirm.sms.code.di

import com.example.test.screens.confirm.sms.code.data.network.SentCodeApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ConfirmSMSCodeProvideModule {

    @Provides
    fun provideSentCodeApi(retrofit: Retrofit): SentCodeApi = retrofit.create(SentCodeApi::class.java)
}