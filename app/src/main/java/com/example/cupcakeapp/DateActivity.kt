package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cupcakeapp.databinding.ActivityDateBinding

class DateActivity : AppCompatActivity() {

    private var extraDate = intent?.extras

    private var extraFlavor: String? = null
    private var extraPrice: String? = null
    private var extraQuantity: String? = null
    private lateinit var binding: ActivityDateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        extraDate = intent?.extras

        with(binding){

        }

        binding.totalPrice.text = extraDate?.getString("chave_preco")

        binding.nextBtn.setOnClickListener {
            processingData(extraDate)
            savingDataInBundle(choosedDate, extraDate)
            nextScreen(choosedDate, extraPrice, extraQuantity, extraFlavor)

        }
        binding.cancelDate.setOnClickListener {
            backScreen()
        }
    }

    private fun nextScreen(
        choosedDate: String,
        extraPrice: String,
        extraQuantity: String,
        extraFlavor: String
    ) {
        val intent = Intent(this, OrderSumarryActivity::class.java)
        with(intent){
            putExtra("chave_data", choosedDate)
            putExtra("chave_preco", extraPrice)
            putExtra("chave_quantidade", extraQuantity)
            putExtra("chave_sabor", extraFlavor)
        }
        Log.d("testeData", "$choosedDate")
        startActivity(intent)
    }

    private fun backScreen() {
        val intentCancelFlavor = Intent(this, MainActivity::class.java)
        startActivity(intentCancelFlavor)
        finish()
    }

    private fun recebeExtras() {
        /** recebendo o dado quantidade da primeira tela*/
        val extraDate = intent?.extras
        processingData(binding, extraDate)
    }

    private fun processingData(extrasData: Bundle?) {
        val choosedDateStr = when (binding.dateOptions.checkedRadioButtonId) {
            R.id.monday -> R.string.monday
            R.id.tuesday -> R.string.tuesday
            R.id.wednesday -> R.string.wednesday
            R.id.thursday -> R.string.thursday
            else -> R.string.friday

        }
        savingDataInBundle(choosedDateStr.toString() /** corrigir para n precisar do toString()**/, extrasData)
    }

    private fun savingDataInBundle(choosedDateStr: String, extrasData: Bundle?) {

        choosedDate = choosedDateStr
        extraPrice = extrasData?.getString("chave_preco").toString()
        extraQuantity = extrasData?.getString("chave_quantidade").toString()
        extraFlavor = extrasData?.getString("chave_sabor").toString()
        nextScreen(binding, choosedDate, extraPrice, extraQuantity,extraFlavor)
    }
}