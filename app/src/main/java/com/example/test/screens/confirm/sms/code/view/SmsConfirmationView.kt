package com.example.test.screens.confirm.sms.code.view

import android.content.Context
import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.inputmethod.BaseInputConnection
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import android.widget.LinearLayout
import android.widget.Space
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.postDelayed
import com.example.test.R
import com.example.test.common.extensions.digits
import com.example.test.common.extensions.hideKeyboard
import com.example.test.common.extensions.showKeyboard
import com.example.test.common.extensions.toPx
import com.example.test.common.utils.emptyString

private const val KEYBOARD_AUTO_SHOW_DELAY = 500L

class SmsConfirmationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var style = Style.getDefault(context)
    private var onChangeListener: OnChangeListener? = null

    var enteredCode: String = emptyString
        set(value) {
            val digits = value.digits()
            field = digits
            style = style.copy(errorColor = null)
            onChangeListener?.onCodeChange(digits, digits.length == codeLength)
            setupSymbolSubviews()
        }

    //Так как по тз сказано "динамическое построение количества возможных вводимых цифр от 4 до 6",
    // но не понятно при каких условиях, то добавлен этот параметр для возможности расширения
    var codeLength: Int = style.codeLength
        set(value) {
            field = value
            style = style.copy(codeLength = value)
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var lineColor: Int = style.symbolViewStyle.lineColor
        set(value) {
            field = value
            style = style.copy(symbolViewStyle = style.symbolViewStyle.copy(lineColor = value))
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var symbolColor: Int = style.symbolViewStyle.textColor
        set(value) {
            field = value
            style = style.copy(symbolViewStyle = style.symbolViewStyle.copy(textColor = value))
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var lineColorActive: Int = style.symbolViewStyle.lineColorActive
        set(value) {
            field = value
            style = style.copy(symbolViewStyle = style.symbolViewStyle.copy(lineColorActive = value))
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var symbolSize: Int = style.symbolViewStyle.textSize
        set(value) {
            field = value
            style = style.copy(symbolViewStyle = style.symbolViewStyle.copy(textSize = value))
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var marginSymbolAndLine: Int = style.symbolViewStyle.marginSymbolAndLine
        set(value) {
            field = value
            style = style.copy(symbolViewStyle = style.symbolViewStyle.copy(marginSymbolAndLine = value))
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var lineWidth: Int = style.symbolViewStyle.lineWidth
        set(value) {
            field = value
            style = style.copy(symbolViewStyle = style.symbolViewStyle.copy(lineWidth = value))
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var spacingSymbol: Int = style.symbolsSpacing
        set(value) {
            field = value
            style = style.copy(symbolsSpacing = value)
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    var symbolFont: Typeface = style.symbolViewStyle.typeface
        set(value) {
            field = value
            style = style.copy(symbolViewStyle = style.symbolViewStyle.copy(typeface = value))
            setupSymbolSubviews()
            invalidate()
            requestLayout()
        }

    fun setOnChangeListener(onChangeListener: OnChangeListener) {
        this.onChangeListener = onChangeListener
    }

    fun setError(@ColorInt colorErrorId: Int) {
        style = style.copy(errorColor = colorErrorId)
        setupSymbolSubviews()
    }

    init {
        orientation = HORIZONTAL
        isFocusable = true
        isFocusableInTouchMode = true

        val attributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.SmsConfirmationView,
            defStyleAttr,
            defStyleRes
        )
        codeLength = attributes.getInteger(R.styleable.SmsConfirmationView_code_length, codeLength)
        val symbolFontId = attributes.getResourceId(R.styleable.SmsConfirmationView_android_fontFamily, 0)
        symbolFont = getTypeface(symbolFontId, context)
        lineColor = attributes.getColor(R.styleable.SmsConfirmationView_line_color, lineColor)
        symbolColor = attributes.getColor(R.styleable.SmsConfirmationView_symbol_color, symbolColor)
        lineColorActive = attributes.getColor(R.styleable.SmsConfirmationView_line_color_active, lineColorActive)
        symbolSize = attributes.getDimensionPixelSize(R.styleable.SmsConfirmationView_symbol_size, symbolSize)
        lineWidth = attributes.getDimensionPixelSize(R.styleable.SmsConfirmationView_line_width, lineWidth)
        marginSymbolAndLine = attributes.getDimensionPixelSize(R.styleable.SmsConfirmationView_margin_symbol_and_line, marginSymbolAndLine)
        spacingSymbol = attributes.getDimensionPixelSize(R.styleable.SmsConfirmationView_spacing_symbol, spacingSymbol)

        setListener()

        attributes.recycle()
    }

    private fun getTypeface(symbolFontId: Int, context: Context) = if (symbolFontId == 0)
        Typeface.DEFAULT
    else
        ResourcesCompat.getFont(context, symbolFontId) ?: Typeface.DEFAULT

    private fun setListener() {
        setOnClickListener {
            if (requestFocus()) {
                showKeyboard()
            }
        }
    }

    private fun setupSymbolSubviews() {
        removeAllViews()

        for (index in 0 until codeLength) {
            addSymbolView(index)

            if (index < codeLength.dec()) {
                addSpace()
            }
        }
    }

    private fun addSymbolView(index: Int) {
        val errorColor = style.errorColor
        val style = if (errorColor != null) {
            style.symbolViewStyle.copy(
                lineColor = errorColor,
                textColor = errorColor,
                lineColorActive = errorColor
            )
        } else {
            style.symbolViewStyle
        }

        val symbolView = SymbolView(context, style)
        symbolView.state = SymbolView.State(
            symbol = enteredCode.getOrNull(index),
            isActive = (index == enteredCode.length)
        )
        addView(symbolView)
    }

    private fun addSpace() {
        val space = Space(context).apply {
            layoutParams = ViewGroup.LayoutParams(style.symbolsSpacing, 0)
        }
        addView(space)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setOnKeyListener { _, keyCode, event -> handleKeyEvent(keyCode, event) }

        postDelayed(KEYBOARD_AUTO_SHOW_DELAY) {
            requestFocus()
            showKeyboard()
        }
    }


    private fun handleKeyEvent(keyCode: Int, event: KeyEvent): Boolean = when {
        event.action != KeyEvent.ACTION_DOWN -> false
        event.isDigitKey() -> {
            val enteredSymbol = event.keyCharacterMap.getNumber(keyCode)
            appendSymbol(enteredSymbol)
            true
        }

        event.keyCode == KeyEvent.KEYCODE_DEL -> {
            removeLastSymbol()
            true
        }

        event.keyCode == KeyEvent.KEYCODE_ENTER -> {
            hideKeyboard()
            true
        }

        else -> false
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        with(outAttrs) {
            inputType = InputType.TYPE_CLASS_NUMBER
            imeOptions = EditorInfo.IME_ACTION_DONE
        }
        return object : InputConnectionWrapper(BaseInputConnection(this, false), true) {

            override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
                return if (beforeLength == 1 && afterLength == 0) {
                    sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                            && sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL))
                } else super.deleteSurroundingText(beforeLength, afterLength)
            }
        }
    }

    private fun KeyEvent.isDigitKey(): Boolean {
        return keyCode in KeyEvent.KEYCODE_0..KeyEvent.KEYCODE_9
    }

    private fun appendSymbol(symbol: Char) {
        if (enteredCode.length == codeLength) return

        this.enteredCode += symbol
    }

    private fun removeLastSymbol() {
        if (enteredCode.isEmpty()) return

        this.enteredCode = enteredCode.substring(0, enteredCode.length - 1)
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState: Parcelable? = super.onSaveInstanceState()
        return SavedState(superState, enteredCode)
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }

        super.onRestoreInstanceState(state.superState)
        enteredCode = state.enteredCode
    }

    private class SavedState(
        superState: Parcelable?,
        val enteredCode: String
    ) : BaseSavedState(superState) {

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeString(enteredCode)
        }
    }

    fun interface OnChangeListener {
        fun onCodeChange(code: String, isComplete: Boolean)
    }

    private data class Style(
        val codeLength: Int,
        @Px val symbolsSpacing: Int,
        @ColorInt val errorColor: Int?,
        val symbolViewStyle: SymbolView.Style,
    ) {
        companion object {
            fun getDefault(context: Context) = Style(
                4,
                10.toPx(context).toInt(),
                null,
                SymbolView.Style.getDefault(context)
            )
        }
    }
}