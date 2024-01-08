package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cupcakeapp.databinding.ActivityMainBinding
import java.text.NumberFormat
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnNext.setOnClickListener {
                if((itQuantity.text?.isEmpty() != true) && (itQuantity.text?.toString() != "0")){
                    processingData()
                } else {
                    itQuantityBox.apply {
                        isErrorEnabled = true
                        error = getString(R.string.error_login)
                    }
                }
            }
        }
    }

    private fun processingData() {
            val quantity = binding.itQuantity.text.toString().trim().toInt()
            val cupcakeCost = (quantity*4)
            val formattedCupcakeCost = NumberFormat.getCurrencyInstance().format(cupcakeCost)
            val priceInStr = getString(R.string.subtotal, formattedCupcakeCost)
            nextScreen(quantity, priceInStr)
        }
    private fun nextScreen(quantity: Int, priceInStr: String) {
        val intent = Intent(this, FlavorActivity::class.java)
        intent.putExtra("chave_quantidade", quantity)
        intent.putExtra("chave_preco", priceInStr)
        startActivity(intent)
    }
}