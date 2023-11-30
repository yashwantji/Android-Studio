package com.example.myapplication2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightKg = findViewById<EditText>(R.id.weightkg)
        val heigthFt = findViewById<EditText>(R.id.heigthft)
        val heigthIn = findViewById<EditText>(R.id.heigthIn)
        val calbtn = findViewById<Button>(R.id.btn)


         calbtn.setOnClickListener {
            val weight = weightKg.text.toString()
             val htft = heigthFt.text.toString()
             val heightInch =heigthIn.text.toString()

             if (validateInput(weight,htft,heightInch)) {
                 val ftcm = ((htft.toFloat() * 30.48) + (heightInch.toFloat() * 2.54))

                 val bmiResult =
                     weight.toFloat() / ((ftcm.toFloat() / 100) * (ftcm.toFloat() / 100))
                 val bmi2Digits = String.format("%.2f", bmiResult).toFloat()
                 displayResult(bmi2Digits)
             }
        }
    }

    private fun validateInput(weight:String?,htft:String?,heightInch:String?):Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_LONG).show()
                return false
            }

            htft.isNullOrEmpty() ->{
                Toast.makeText(this,"Heigth in Feet is Empty",Toast.LENGTH_LONG).show()
                return false
            }

            heightInch.isNullOrEmpty() ->{
                Toast.makeText(this,"Height in Inch is Empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResult(bmiResult:Float){
        val resultShow = findViewById<TextView>(R.id.txtResult)
        val resultPosition = findViewById<TextView>(R.id.txtPosition)
        val resultInfo = findViewById<TextView>(R.id.txtInfo)

        resultShow.text = bmiResult.toString()
        resultInfo.text = "(Normal Range is 18.50 to 24.99 )"

           var resultText = ""
            var color = 0

        when{
            bmiResult<18.50 ->{
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmiResult in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmiResult in 25.00..29.99 ->{
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmiResult>29.99 ->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultPosition.setTextColor(ContextCompat.getColor(this,color))
        resultPosition.text = resultText
    }
}

