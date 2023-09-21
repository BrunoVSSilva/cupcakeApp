package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import com.example.cupcakeapp.databinding.ActivityFlavorBinding
import com.example.cupcakeapp.databinding.ActivityOrderSumarryBinding

class OrderSumarryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderSumarryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityOrderSumarryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showOverView(binding)

        binding.finalizar.setOnClickListener {
            orderFinalization(binding)
        }
        binding.cancelPedido.setOnClickListener {
            cancelOrder(binding)
        }
    }

    private fun showOverView(binding: ActivityOrderSumarryBinding){
        intent.extras?.apply {
            binding.run {
                quantidadeReal.text = getString("chave_quantidade")
                saborReal.text = getString("chave_sabor")
                dataRetiradaReal.text = getString("chave_data")
                valorTotal.text = getString("chave_preco")
            }
        }
    }
    private fun orderFinalization(binding: ActivityOrderSumarryBinding){
        val alertaDeCompra = AlertDialog.Builder(this)
            .setTitle(R.string.alertTitle)
            .setMessage(R.string.alertMessage)
            .show()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
    private fun cancelOrder(binding: ActivityOrderSumarryBinding){
        val intentCancelPedido = Intent(this, MainActivity::class.java)
        startActivity(intentCancelPedido)
        finish()
    }
}