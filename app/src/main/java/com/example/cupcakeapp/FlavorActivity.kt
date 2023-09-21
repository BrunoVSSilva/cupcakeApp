package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cupcakeapp.databinding.ActivityFlavorBinding

class FlavorActivity : AppCompatActivity() {

    private lateinit var saborEscolido: String
    private lateinit var extraPreco: String
    private lateinit var extraQuantidade: String
    private lateinit var binding: ActivityFlavorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFlavorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** recebendo os extras da tela anterior*/
        val extrasSabor = intent?.extras
        extraPreco = extrasSabor?.getString("chave_preco").toString()

        binding.valorTotal.text = extraPreco

        binding.nextBtn.setOnClickListener {
            processandoDado(binding, extrasSabor)
            armazenandoDados(binding, saborEscolido, extrasSabor)
            proximaTela(binding, saborEscolido, extraPreco, extraQuantidade)
        }

        binding.cancelFlavor.setOnClickListener {
            voltar(binding)
        }
    }
    private fun proximaTela(
        binding: ActivityFlavorBinding,
        saborEscolhidoStr: String,
        extraPreco: String,
        extraQuantidade: String
    ){
        val intent = Intent(this, DateActivity::class.java)
        intent.putExtra("chave_sabor", saborEscolhidoStr)
        intent.putExtra("chave_preco", extraPreco)
        intent.putExtra("chave_quantidade", extraQuantidade)
        startActivity(intent)
    }
    private fun voltar(binding: ActivityFlavorBinding){
        val intentCancelFlavor = Intent(this, MainActivity::class.java)
        startActivity(intentCancelFlavor)
        finish()
    }

    private fun recebeExtras(binding: ActivityFlavorBinding){
        /** recebendo o dado quantidade da primeira tela*/
        val extrasSabor = intent?.extras
        processandoDado(binding, extrasSabor)
    }
    private fun processandoDado(binding: ActivityFlavorBinding, extrasSabor: Bundle?){
        val saborEscolhidoStr = when (binding.saborOpcoes.checkedRadioButtonId){
            R.id.opcao1 -> "Morango"
            R.id.opcao2 -> "Chocolate"
            R.id.opcao3 -> "Café"
            R.id.opcao4 -> "Red Velvet"
            else -> "Caramelo"
        }
        armazenandoDados(binding, saborEscolhidoStr, extrasSabor)
    }

    private fun armazenandoDados(
        binding: ActivityFlavorBinding,
        saborEscolhidoStr: String,
        extrasSabor: Bundle?
    ){
        saborEscolido = saborEscolhidoStr
        extraPreco = extrasSabor?.getString("chave_preco").toString()
        extraQuantidade = extrasSabor?.getString("chave_quantidade").toString()
        

        proximaTela(binding, saborEscolido, extraPreco, extraQuantidade)
    }
}