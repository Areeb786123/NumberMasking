package com.areeb.myapplication

import android.content.Context
import android.util.AttributeSet

class NumberMaskingEditText : androidx.appcompat.widget.AppCompatEditText {
    private var previousLength: Int = 0

    companion object {
        private const val ZERO = 0
        private const val FOUR = 4
        private const val NINE = 9
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)

        if (text == null || text.length < previousLength) {
            previousLength = text?.length ?: ZERO
            return
        }

        val maskedNumber = StringBuilder()
        var i = ZERO
        while (i < text.length) {
            val char = text[i]
            if (char.isDigit()) {
                if (maskedNumber.length == ZERO) {
                    maskedNumber.append("(")
                } else if (maskedNumber.length == FOUR) {
                    maskedNumber.append(") ")
                } else if (maskedNumber.length == NINE) {
                    maskedNumber.append("-")
                }
                maskedNumber.append(char)
            } else {
                maskedNumber.append(char)
            }
            i++
        }
        previousLength = maskedNumber.length

        if (maskedNumber.toString().length <= 14 && maskedNumber.toString() != text.toString()) {
            setText(maskedNumber)
            setSelection(previousLength)
        }
    }
}
