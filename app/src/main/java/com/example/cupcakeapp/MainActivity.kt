package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cupcakeapp.databinding.ActivityMainBinding
import java.text.NumberFormat
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var quantityStr: String = ""
    var princeInStr: String = ""
    /** !!!!!!!!INPUT n DEVE SER CRIADO EM FUNÇÃO!!!!!!!!!!*/
   /** Alterar função quantidadeStr para n ser criada em função*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            if(itsNullZeroOrNot(binding)){
             processingData(binding)
             savingDataInBundle(binding, quantityStr, princeInStr)
             nextScreen(binding, quantityStr, princeInStr)
            }
            else {
                setErrorifNullOrZero(binding)
            }
        }
    }

    private fun setErrorifNullOrZero(binding: ActivityMainBinding){
        binding.apply {
            itQuantityBox.isErrorEnabled = true
            itQuantityBox.error = getString(R.string.error_login)
        }
    }

    private fun isNotNullOrZero(binding: ActivityMainBinding){
        binding.apply {
            itQuantityBox.isErrorEnabled = false
        }
    }

    private fun checkNull(binding: ActivityMainBinding): Boolean{
        val quantityStr = binding.itQuantity.text.toString()
        if(quantityStr.isEmpty().not()){
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
        val quantityInt = binding.itQuantity.text.toString().trim().toInt()
        if (quantityInt != 0) {
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

    private fun processingData(binding: ActivityMainBinding) {
        if (itsNullZeroOrNot(binding)){
            val quantity = binding.itQuantity.text.toString().trim().toInt()
            val cupcakeCost = (quantity*4)
            val formattedCupcakeCost = NumberFormat.getCurrencyInstance().format(cupcakeCost)
            val priceInStr = getString(R.string.subtotal, formattedCupcakeCost)
            savingDataInBundle(binding, quantity.toString(), priceInStr)
        }
    }
    private fun savingDataInBundle(binding: ActivityMainBinding, quantity: String, priceInStr: String){
        quantityStr = quantity
        princeInStr = priceInStr

        nextScreen(binding, quantityStr!!, princeInStr)
    }

    private fun nextScreen(binding: ActivityMainBinding, quantityStr: String, priceInStr: String) {
        val intent = Intent(this, FlavorActivity::class.java)
        Log.d("testeExtra", "$quantityStr")
        Log.d("testeExtra", "$priceInStr")
        intent.putExtra("chave_quantidade", quantityStr)
        intent.putExtra("chave_preco", priceInStr)
        startActivity(intent)
    }
}