package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cupcakeapp.databinding.ActivityDateBinding
import com.example.cupcakeapp.databinding.ActivityFlavorBinding

class DateActivity : AppCompatActivity() {

    private lateinit var dataEscolhida: String
    private lateinit var extrasSabor: String
    private lateinit var extraPreco: String
    private lateinit var extraQuantidade: String
    private lateinit var binding: ActivityDateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extrasData = intent?.extras

        binding.valorTotal.text = extrasData?.getString("chave_preco")

        binding.nextBtn.setOnClickListener {
            processandoDado(binding, extrasData)
            armazenandoDados(binding, dataEscolhida, extrasData)
            proximaTela(binding, dataEscolhida, extraPreco, extraQuantidade, extrasSabor)

        }
        binding.cancelDate.setOnClickListener {
            voltar(binding)
        }

    }

    private fun proximaTela(
        binding: ActivityDateBinding,
        dataEscolhida: String,
        extraPreco: String,
        extraQuantidade: String,
        extrasSabor: String
    ) {
        val intent = Intent(this, OrderSumarryActivity::class.java)
        intent.putExtra("chave_data", dataEscolhida)
        intent.putExtra("chave_preco", extraPreco)
        intent.putExtra("chave_quantidade", extraQuantidade)
        intent.putExtra("chave_sabor", extrasSabor)
        Log.d("testeData", "$dataEscolhida")
        startActivity(intent)
    }

    private fun voltar(binding: ActivityDateBinding) {
        val intentCancelFlavor = Intent(this, MainActivity::class.java)
        startActivity(intentCancelFlavor)
        finish()
    }

    private fun recebeExtras(binding: ActivityDateBinding) {
        /** recebendo o dado quantidade da primeira tela*/
        val extrasData = intent?.extras
        processandoDado(binding, extrasData)
    }

    private fun processandoDado(binding: ActivityDateBinding, extrasData: Bundle?) {
        val dataEscolhidaStr = when (binding.saborOpcoes.checkedRadioButtonId) {
            R.id.opcao1 -> "Segunda-Feira"
            R.id.opcao2 -> "Terça-feira"
            R.id.opcao3 -> "Quarta-feira"
            R.id.opcao4 -> "Quinta-feira"
            else -> "Sexta-feira"
        }
        armazenandoDados(binding, dataEscolhidaStr, extrasData)
    }

    private fun armazenandoDados(binding: ActivityDateBinding, saborEscolhidoStr: String, extrasData: Bundle?) {

        dataEscolhida = saborEscolhidoStr
        extraPreco = extrasData?.getString("chave_preco").toString()
        extraQuantidade = extrasData?.getString("chave_quantidade").toString()
        extrasSabor = extrasData?.getString("chave_sabor").toString()
        proximaTela(binding, dataEscolhida, extraPreco, extraQuantidade,extrasSabor)
    }
}