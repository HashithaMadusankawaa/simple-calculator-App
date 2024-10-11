package com.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var inputTextView: TextView
    private lateinit var OutputTextView: TextView

    private var input : String = " "
    private  var opera1 : Double = 0.0
    private  var opera2 : Double = 0.0
    private  var operation : String = " "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        inputTextView = findViewById(R.id.input)
        OutputTextView = findViewById(R.id.output)

        var button = listOf<Button>(
                    findViewById(R.id.button0),
                    findViewById(R.id.button1),
                    findViewById(R.id.button2),
                    findViewById(R.id.button3),
                    findViewById(R.id.button4),
                    findViewById(R.id.button5),
                    findViewById(R.id.button6),
                    findViewById(R.id.button7),
                    findViewById(R.id.button8),
                    findViewById(R.id.button9),
                    findViewById(R.id.button_clear),
                    findViewById(R.id.button_devide),
                    findViewById(R.id.button_equal),
                    findViewById(R.id.button_min),
                    findViewById(R.id.button_multi),
                    findViewById(R.id.button_plus),
                    findViewById(R.id.button_para1),
                    findViewById(R.id.button_para2),
                    findViewById(R.id.button_para3)
        )
        button.forEach { button ->
            button.setOnClickListener(View.OnClickListener{
                handleButtonClick(button.text.toString())
            })
        }


    }


    private fun appenInput(value: String){
        input += value
        inputTextView.text = input


    }

    private fun appendDecimal(){
        if (!input.contains(".")){
            input += "."
            inputTextView.text = input
        }
    }

    private  fun handleOperator(op:String){
        operation = op
        opera1 = input.toDouble()
        input = ""
        inputTextView.text = " "
    }

    private fun calculatorResult(){
        if (input.isNotEmpty()){
            opera2 = input.toDouble()
            val result = when (operation){
                "+" -> opera1 + opera2
                "-" -> opera1 - opera2
                "*" -> opera1 * opera2
                "/" -> opera1 / opera2
                else->throw IllegalStateException("Invalid Operator")
            }

            OutputTextView.text = result.toString()
            input =result.toString()
            inputTextView.text = input

        }
    }

    private fun clearInput(){
        input = ""
        opera1 = 0.0
        opera2 = 0.0
        operation = ""
        inputTextView.text = " "
        OutputTextView.text = " "
    }

    private fun hadlePresantage(){
        if (input.isNotEmpty()){
            val value = input.toDouble()/100
            input = value.toString()
            inputTextView.text = input

        }
    }

    private fun toggleSign(){
        if (input.isNotEmpty() && input != "0"){
            val  value = input.toDouble()* -1
            input = value.toString()
            inputTextView.text = input
        }
    }

    private fun String.isNumeric():Boolean{
       return try {
                this.toDouble()
           true
        }catch (e:NumberFormatException){
            false
        }
    }

    private fun handleButtonClick(value: String) {
        when {
            value.isNumeric() -> appenInput(value)
            value == "." -> appendDecimal()
            value in setOf("+", "-", "*", "/") -> handleOperator(value)
            value == "=" -> calculatorResult()
            value == "AC" -> clearInput()
            value == "%" -> hadlePresantage()
            value == "+/-" -> toggleSign()
        }
    }

}
