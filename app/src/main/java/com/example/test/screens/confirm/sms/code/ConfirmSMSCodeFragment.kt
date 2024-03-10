package com.example.test.screens.confirm.sms.code

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.test.R
import com.example.test.common.application.appComponent
import com.example.test.common.binding.fragment.BindingFragment
import com.example.test.common.extensions.observe
import com.example.test.common.view.model.factory.assistedViewModel
import com.example.test.databinding.FragmentConfirmSmsCodeBinding
import com.example.test.screens.confirm.sms.code.di.DaggerConfirmSMSCodeComponent
import com.example.test.screens.confirm.sms.code.domain.model.ErrorSmsCode
import com.example.test.screens.confirm.sms.code.domain.model.TimerState
import javax.inject.Inject

private const val SPACE = " "

class ConfirmSMSCodeFragment : BindingFragment<FragmentConfirmSmsCodeBinding>(FragmentConfirmSmsCodeBinding::inflate) {

    @Inject
    lateinit var factory: ConfirmSMSCodeViewModel.Factory
    private val viewModel by assistedViewModel { factory.create() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerConfirmSMSCodeComponent.factory().create(context.appComponent).inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setupToolbar(getString(R.string.title_toolbar))
        setupConfirmationCodeSms()
        setupTimerResendCode()

    }

    private fun setupConfirmationCodeSms() {
        binding.codeSms.setOnChangeListener { code, isComplete  ->
            if (isComplete) viewModel.sentCode(code)
            binding.errorDescription.isInvisible = true
        }
        viewModel.errorCodeSms.observe(viewLifecycleOwner) {
            handleErrorSmsCode(it)
        }
    }

    private fun handleErrorSmsCode(error: ErrorSmsCode) {
        when (error) {
            is ErrorSmsCode.IncorrectSmsCode -> setErrorSmsCode(error.descriptionErrorId)
        }
    }

    private fun setErrorSmsCode(descriptionErrorId: Int) {
        binding.errorDescription.text = getString(descriptionErrorId)
        binding.errorDescription.isInvisible = false
        binding.codeSms.setError(requireContext().getColor(R.color.red))
    }

    private fun setupTimerResendCode() {
        viewModel.timerState.observe(viewLifecycleOwner) {
            handleTimerState(it)
        }
        binding.resend.setOnClickListener {
            viewModel.resentCode()
        }
    }

    private fun handleTimerState(it: TimerState) {
        when (it) {
            is TimerState.Start -> setStartTimer(it.time)
            TimerState.Stop -> setResendCode()
        }
    }

    private fun setStartTimer(time: String) {
        val spannableTime = getTimeSpannable(time)
        binding.timer.text = appendTextAndTimer(spannableTime)
        binding.resend.isVisible = false
        binding.timer.isVisible = true
    }

    private fun appendTextAndTimer(time: SpannableString) =
        SpannableStringBuilder().append(getString(R.string.timer_sms_code)).append(SPACE).append(time)

    private fun getTimeSpannable(time: String): SpannableString {
        val spannableTime = SpannableString(time)
        spannableTime.setSpan(
            ForegroundColorSpan(requireContext().getColor(R.color.black)),
            0,
            time.length,
            0
        )
        spannableTime.setSpan(AbsoluteSizeSpan(14, true), 0, time.length, 0)
        return spannableTime
    }

    private fun setResendCode() {
        binding.timer.isVisible = false
        binding.resend.isVisible = true
    }
}