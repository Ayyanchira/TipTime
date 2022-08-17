package com.ideas2app.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ideas2app.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val cost = binding.costOfServiceEditText.text.toString().toDoubleOrNull()
        binding.tipResult.visibility = View.VISIBLE
        if(cost == null) {
            binding.tipResult.text = ""
            Toast.makeText(this, "Cost of service cannot be empty.", Toast.LENGTH_SHORT).show()
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_fifteen_percent -> 0.15
            R.id.option_eighteen_percent -> 0.18
            else -> 0.20
        }

        val tip = tipPercentage * cost
        var total:Double = cost + tip
        if (binding.roundUpSwitch.isChecked) {
            total = kotlin.math.ceil(total)
        }

        val formattedCost = NumberFormat.getCurrencyInstance().format(total)
        binding.tipResult.text = getString(R.string.tip_amount, formattedCost)
    }
}