package com.yemam.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yemam.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // this when the user clicks the calculate tip button.
        binding.btnCalculateTip.setOnClickListener { calculateTip() }
    }

    // this function is to calculate the tips for the user.
    private fun calculateTip() {
        // get the price entered by the user
        val price = binding.etTextInput.text.toString().toDoubleOrNull()

        // this is handle to the error when the user don't enter a text then press the button
        if (price == null) {
            // this is to set the value of the tips to the user if he didn't enter a value
            binding.tvResultTip.text = getString(R.string.error_output)

            // display a message to the user that he didn't enter a price
            Toast.makeText(this, getString(R.string.please_enter_a_the_price), Toast.LENGTH_LONG)
                .show()
            return
        }

        // get the percentage from the groupRadio
        val tipPercentage = when (binding.rgRatingService.checkedRadioButtonId) {
            R.id.rb_amazing -> .20
            R.id.rb_good -> .18
            else -> .15
        }

        // calculate the tips and assign it to the text of the textView.
        var tip = price * tipPercentage

        // we have to round up the value
        if (binding.roundupSwitch.isChecked)
            tip = ceil(tip)

        // display the currency to the user according to the language used in the device
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // display the tips to the user.
        binding.tvResultTip.text = getString(R.string.final_output, formattedTip)

    }
}