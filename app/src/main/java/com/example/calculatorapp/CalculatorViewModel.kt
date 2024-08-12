package com.example.calculatorapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Stack

class CalculatorViewModel : ViewModel() {

    private val _inputDisplay = MutableLiveData<String>()
    val inputDisplay: LiveData<String> = _inputDisplay

    private val _resultDisplay = MutableLiveData<String>()
    val resultDisplay: LiveData<String> = _resultDisplay

    private val inputStringBuilder = StringBuilder()

    init {
        _inputDisplay.value = ""
        _resultDisplay.value = ""
    }

    fun onDigit(digit: String) {
        inputStringBuilder.append(digit)
        _inputDisplay.value = inputStringBuilder.toString()
    }

    fun onDot() {
        // Prevent multiple dots in a single number
        if (inputStringBuilder.isEmpty() || inputStringBuilder.last() == '.') return
        inputStringBuilder.append(".")
        _inputDisplay.value = inputStringBuilder.toString()
    }

    fun onOperation(operation: String) {
        if (inputStringBuilder.isNotEmpty() && inputStringBuilder.last().isDigit()) {
            inputStringBuilder.append(" $operation ")
            _inputDisplay.value = inputStringBuilder.toString()
        }
    }

    fun onClear() {
        inputStringBuilder.clear()
        _inputDisplay.value = ""
        _resultDisplay.value = ""
    }

    fun onBackspace() {
        if (inputStringBuilder.isNotEmpty()) {
            inputStringBuilder.deleteCharAt(inputStringBuilder.length - 1)
            _inputDisplay.value = inputStringBuilder.toString()
        }
    }

    fun onEquals() {
        try {
            val result = evaluateExpression(inputStringBuilder.toString())
            _resultDisplay.value = result.toString()
            _inputDisplay.value = inputStringBuilder.toString()
        } catch (e: Exception) {
            _resultDisplay.value = "Error"
        }
    }

    private fun evaluateExpression(expression: String): Double {
        // Simple implementation using stacks for parsing and calculating the expression
        val tokens = expression.split(" ")
        val values = Stack<Double>()
        val operators = Stack<String>()

        for (token in tokens) {
            when {
                token.isDouble() -> values.push(token.toDouble())
                token.isOperator() -> operators.push(token)
            }
        }

        while (operators.isNotEmpty()) {
            val operator = operators.pop()
            val b = values.pop()
            val a = values.pop()

            val result = when (operator) {
                "+" -> a + b
                "-" -> a - b
                "*" -> a * b
                "/" -> a / b
                else -> 0.0
            }
            values.push(result)
        }

        return if (values.isNotEmpty()) values.pop() else 0.0
    }

    private fun String.isDouble(): Boolean {
        return this.toDoubleOrNull() != null
    }

    private fun String.isOperator(): Boolean {
        return this in listOf("+", "-", "*", "/")
    }
}
