package edu.uw.ischool.dkaria.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val tipPercentages = arrayOf("10%", "15%", "18%", "20%")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipPercentages)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                button.isEnabled = s.toString().isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        button.setOnClickListener {
            val amount = editText.text.toString().toDouble()
            val tipPercentage = spinner.selectedItem.toString().dropLast(1).toDouble() / 100
            val tipAmount = amount * tipPercentage

            val decimalFormat = DecimalFormat("#.00")
            Toast.makeText(this, "Tip Amount: $${decimalFormat.format(tipAmount)}", Toast.LENGTH_LONG).show()
        }
    }
}

