package com.example.calculatorapp


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe LiveData for input and result display
        viewModel.inputDisplay.observe(this, Observer {
            binding.tvInput.text = it
        })
        viewModel.resultDisplay.observe(this, Observer {
            binding.tvResult.text = it
        })

        setupButtonClickListeners()
    }

    private fun setupButtonClickListeners() {
        binding.btn0.setOnClickListener { viewModel.onDigit("0") }
        binding.btn1.setOnClickListener { viewModel.onDigit("1") }
        binding.btn2.setOnClickListener { viewModel.onDigit("2") }
        binding.btn3.setOnClickListener { viewModel.onDigit("3") }
        binding.btn4.setOnClickListener { viewModel.onDigit("4") }
        binding.btn5.setOnClickListener { viewModel.onDigit("5") }
        binding.btn6.setOnClickListener { viewModel.onDigit("6") }
        binding.btn7.setOnClickListener { viewModel.onDigit("7") }
        binding.btn8.setOnClickListener { viewModel.onDigit("8") }
        binding.btn9.setOnClickListener { viewModel.onDigit("9") }

        binding.btnDot.setOnClickListener { viewModel.onDot() }

        binding.btnAdd.setOnClickListener { viewModel.onOperation("+") }
        binding.btnSubtract.setOnClickListener { viewModel.onOperation("-") }
        binding.btnMultiply.setOnClickListener { viewModel.onOperation("*") }
        binding.btnDivide.setOnClickListener { viewModel.onOperation("/") }

        binding.btnClear.setOnClickListener { viewModel.onClear() }
        binding.btnBackspace.setOnClickListener { viewModel.onBackspace() }
        binding.btnEqual.setOnClickListener { viewModel.onEquals() }
    }
}
