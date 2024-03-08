package com.example.test.screens.confirm.sms.code

import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ConfirmSMSCodeViewModel @AssistedInject constructor() : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(): ConfirmSMSCodeViewModel
    }
}