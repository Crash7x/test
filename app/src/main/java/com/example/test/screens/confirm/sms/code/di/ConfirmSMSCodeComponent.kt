package com.example.test.screens.confirm.sms.code.di

import com.example.test.common.application.di.AppComponent
import dagger.Component
import com.example.test.screens.confirm.sms.code.ConfirmSMSCodeFragment

@ConfirmSMSCodeScope
@Component(
    modules = [
        ConfirmSMSCodeBindModule::class
    ],
    dependencies = [AppComponent::class]
)

interface ConfirmSMSCodeComponent {

    fun inject(fragment: ConfirmSMSCodeFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ConfirmSMSCodeComponent
    }
}