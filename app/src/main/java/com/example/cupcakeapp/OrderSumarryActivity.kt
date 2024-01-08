package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import com.example.cupcakeapp.databinding.ActivityOrderSumarryBinding

class OrderSumarryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderSumarryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderSumarryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showOverview(binding)
        with(binding){
            finish.setOnClickListener {
                orderFinalization(binding)
            }
            cancelOrder.setOnClickListener {
                cancelOrder(binding)
            }
        }
    }

    private fun showOverview(binding: ActivityOrderSumarryBinding){
        intent.extras?.apply {
            binding.run {
                orderedQuantity.text = getString("chave_quantidade")
                orderedFlavor.text = getString("chave_sabor")
                orderedDeliveryDate.text = getString("chave_data")
                totalPrice.text = getString("chave_preco")
            }
        }
    }
    private fun orderFinalization(binding: ActivityOrderSumarryBinding){
        val finalizationAlert = AlertDialog.Builder(this)
            .setTitle(R.string.alertTitle)
            .setMessage(R.string.alertMessage)
            .show()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
    private fun cancelOrder(binding: ActivityOrderSumarryBinding){
        val intentCancerOrder = Intent(this, MainActivity::class.java)
        startActivity(intentCancerOrder)
        finish()
    }
}