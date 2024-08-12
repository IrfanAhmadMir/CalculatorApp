package com.example.calculatorapp


class CalculatorModel {
    fun add(a: Double, b: Double) = a + b
    fun subtract(a: Double, b: Double) = a - b
    fun multiply(a: Double, b: Double) = a * b
    fun divide(a: Double, b: Double): Double? = if (b != 0.0) a / b else null
}
