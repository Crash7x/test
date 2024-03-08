package com.example.test.screens.confirm.sms.code

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.test.common.application.appComponent
import com.example.test.common.binding.fragment.BindingFragment
import com.example.test.common.view.model.factory.assistedViewModel
import com.example.test.databinding.FragmentConfirmSmsCodeBinding
import com.example.test.screens.confirm.sms.code.di.DaggerConfirmSMSCodeComponent
import javax.inject.Inject


class ConfirmSMSCodeFragment : BindingFragment<FragmentConfirmSmsCodeBinding>(FragmentConfirmSmsCodeBinding::inflate) {

    @Inject
    lateinit var factory: ConfirmSMSCodeViewModel.Factory
    val viewModel by assistedViewModel { factory.create() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerConfirmSMSCodeComponent.factory().create(context.appComponent).inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}