package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import com.example.cupcakeapp.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    var quantidadeStr: String = ""
    var valorInTextStr: String = ""

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            processandoQuantidade(binding)
            armazenandoNoBundle(binding, quantidadeStr, valorInTextStr)
            proximaTela(binding, quantidadeStr, valorInTextStr)

        }
    }



    private fun setErrorifNullOrZero(binding: ActivityMainBinding){
        binding.apply {
            itQuantidadeBox.isErrorEnabled = true
            itQuantidadeBox.error = getString(R.string.erroLogin)
        }
    }

    private fun isNotNullOrZero(binding: ActivityMainBinding){
        binding.apply {
            itQuantidadeBox.isErrorEnabled = false
        }
    }

    private fun checkNull(binding: ActivityMainBinding): Boolean{
        val quantidadeStr = binding.itQuantidade.text.toString()
        if(quantidadeStr.isEmpty().not()){
            Log.d("checkIfNull", "Passou não é nulo")
            return true
        } else {
            Log.d("checkIfNull", "não passou, é nulo")
            return false
            }
    }
    /**
     * private fun checkNullWithLet(binding: ActivityMainBinding): Boolean {
    val quantidadeStr = binding.itQuantidade.text.toString()

    return quantidadeStr?.let {
    Log.d("checkIfNull", "Passou não é nulo")
    true // retorna true se quantidadeStr não for nulo
    } ?: run {
    Log.d("checkIfNull", "É nulo")
    false // retorna false se quantidadeStr for nulo
    }
    } */


    private fun checkZero(binding: ActivityMainBinding): Boolean{
        val quantidadeInt = binding.itQuantidade.text.toString().trim().toInt()
        if (quantidadeInt != 0) {
            /** se cair aqui, o input não é 0 */
            Log.d("checkIfZero", "passou, não é 0")
            return true
        } else return false
    }

    private fun itsNullZeroOrNot(binding: ActivityMainBinding): Boolean{
        if (checkNull(binding)){
            Log.d("checkNullAndZero", "passou do null")
            checkNull(binding)
            if (checkZero(binding)){
                Log.d("checkNullAndZero", "passou do null")
                isNotNullOrZero(binding)
                return true
            } else{
                setErrorifNullOrZero(binding)
                return false
            }
        }else{
            setErrorifNullOrZero(binding)
            return false
        }
    }

    private fun processandoQuantidade(binding: ActivityMainBinding) {
        if (itsNullZeroOrNot(binding)){
            val quantidade = binding.itQuantidade.text.toString().trim().toInt()
            val custoCupcake = (quantidade*4)
            val custoCupcakeFormatado = NumberFormat.getCurrencyInstance().format(custoCupcake)
            val valorInText = getString(R.string.valorEstipulado, custoCupcakeFormatado)
            armazenandoNoBundle(binding, quantidade.toString(), valorInText)
        }
    }
    private fun armazenandoNoBundle(binding: ActivityMainBinding, quantidade: String, valorInText: String){
        quantidadeStr = quantidade
        valorInTextStr = valorInText

        proximaTela(binding, quantidadeStr, valorInTextStr)
    }

    private fun proximaTela(binding: ActivityMainBinding, quantidadeStr: String, valorInTextStr: String) {
        val intent = Intent(this, FlavorActivity::class.java)
        Log.d("testeExtra", "$quantidadeStr")
        Log.d("testeExtra", "$valorInTextStr")
        intent.putExtra("chave_quantidade", quantidadeStr)
        intent.putExtra("chave_preco", valorInTextStr)
        startActivity(intent)
    }
}