package com.areeb.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.areeb.myapplication.R.id.maskedNumber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberMasking = findViewById<NumberMaskingEditText>(R.id.numberMasking)
        val maskedNumber = findViewById<TextView>(R.id.maskedNumber)
        val btn = findViewById<Button>(R.id.btn)
        val saveNumber = findViewById<TextView>(R.id.saveNumber)

        numberMasking.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                maskedNumber.text = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                maskedNumber.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                maskedNumber.text = s.toString()
            }
        })

        btn.setOnClickListener {
            saveNumber.text = getNormalNumber(maskedNumber.text.toString())
        }
    }

    fun getNormalNumber(maskedNumber: String): String {
        return maskedNumber.filter { it.isDigit() }
    }
}
