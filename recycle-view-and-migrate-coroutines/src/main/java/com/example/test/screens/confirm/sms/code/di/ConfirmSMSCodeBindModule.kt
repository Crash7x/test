package com.example.test.screens.confirm.sms.code.di

import com.example.test.screens.confirm.sms.code.data.network.SentCodeRepository
import com.example.test.screens.confirm.sms.code.data.network.SentCodeRepositoryImpl
import com.example.test.screens.confirm.sms.code.domain.usecase.TimerUseCase
import com.example.test.screens.confirm.sms.code.domain.usecase.TimerUseCaseImpl
import com.example.test.screens.confirm.sms.code.domain.usecase.ResentCodeUseCase
import com.example.test.screens.confirm.sms.code.domain.usecase.ResentCodeUseCaseImpl
import com.example.test.screens.confirm.sms.code.domain.usecase.SentCodeUseCase
import com.example.test.screens.confirm.sms.code.domain.usecase.SentCodeUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface ConfirmSMSCodeBindModule {

    @Binds
    fun bindSentCodeRepository(repository: SentCodeRepositoryImpl): SentCodeRepository

    @Binds
    fun bindSentCodeUseCase(usecase: SentCodeUseCaseImpl): SentCodeUseCase

    @Binds
    fun bindResentCodeUseCase(usecase: ResentCodeUseCaseImpl): ResentCodeUseCase

    @Binds
    fun bindTimerManager(usecase: TimerUseCaseImpl): TimerUseCase

}