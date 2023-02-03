package com.areeb.myapplication

import android.content.Context
import android.util.AttributeSet

class NumberMaskingEditText : androidx.appcompat.widget.AppCompatEditText {
    private var previousLength: Int = 0

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
            previousLength = text?.length ?: 0
            return
        }

        val maskedNumber = StringBuilder()
        var i = 0
        while (i < text.length) {
            val char = text[i]
            if (char.isDigit()) {
                if (maskedNumber.length == 0) {
                    maskedNumber.append("(")
                } else if (maskedNumber.length == 4) {
                    maskedNumber.append(") ")
                } else if (maskedNumber.length == 9) {
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
