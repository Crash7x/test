package com.example.test.screens.confirm.sms.code.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.util.Size
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Px
import com.example.test.common.extensions.toPx
import com.example.test.common.utils.emptyString

@SuppressLint("ViewConstructor")
internal class SymbolView(context: Context, private val symbolStyle: Style) : View(context) {

    var state: State = State()
        set(value) {
            if (field == value) return
            field = value
            updateState(state)
        }

    private val linePaint: Paint = Paint().apply {
        isAntiAlias = true
        color = symbolStyle.lineColor
        style = Paint.Style.STROKE
        strokeWidth = symbolStyle.lineWidth.toFloat()
    }

    private val textPaint: Paint = Paint().apply {
        isAntiAlias = true
        color = symbolStyle.textColor
        textSize = symbolStyle.textSize.toFloat()
        typeface = Typeface.DEFAULT_BOLD
        textAlign = Paint.Align.CENTER
    }

    private var textSize: Size = calculateTextSize('0')

    private val rect = RectF()

    @Suppress("SameParameterValue")
    private fun calculateTextSize(symbol: Char): Size {
        val textBounds = Rect()
        textPaint.getTextBounds(symbol.toString(), 0, 1, textBounds)
        return Size(textBounds.width(), textBounds.height())
    }

    private fun updateState(state: State) = with(state) {
        if (symbol == null && isActive) {
            linePaint.color = symbolStyle.lineColorActive
        } else {
            linePaint.color = symbolStyle.lineColor
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val fontMetrics = textPaint.getFontMetrics()
        val height = fontMetrics.descent - fontMetrics.ascent
        val w = resolveSizeAndState(textSize.width * 3, widthMeasureSpec, 0)
        val h = resolveSizeAndState(
            height.toInt() * 2 + symbolStyle.marginSymbolAndLine + symbolStyle.lineWidth,
            heightMeasureSpec,
            0
        )
        setMeasuredDimension(w, h)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val lineWidthHalf = linePaint.strokeWidth / 2
        rect.left = lineWidthHalf
        rect.top = lineWidthHalf
        rect.right = measuredWidth.toFloat() - lineWidthHalf
        rect.bottom = measuredHeight.toFloat() - lineWidthHalf
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(
            rect.centerX() - (rect.width() / 2),
            rect.centerY(),
            rect.centerX() + (rect.width() / 2),
            rect.centerY(),
            linePaint
        )

        canvas.drawText(
            state.symbol?.toString() ?: emptyString,
            rect.width() / 2 + linePaint.strokeWidth / 2,
            rect.height() / 2 - linePaint.strokeWidth / 2 - symbolStyle.marginSymbolAndLine,
            textPaint
        )
    }

    data class Style(
        @ColorInt val lineColor: Int,
        @ColorInt val lineColorActive: Int,
        @Px val lineWidth: Int,
        @Px val marginSymbolAndLine: Int,
        @ColorInt val textColor: Int,
        @Px val textSize: Int,
        val typeface: Typeface = Typeface.DEFAULT_BOLD
    ) {
        companion object {
            fun getDefault(context: Context) = Style(
                Color.BLACK,
                Color.BLACK,
                10.toPx(context).toInt(),
                10.toPx(context).toInt(),
                Color.BLACK,
                10.toPx(context).toInt()
            )
        }
    }

    data class State(
        val symbol: Char? = null,
        val isActive: Boolean = false
    )
}