package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cupcakeapp.databinding.ActivityDateBinding

class DateActivity : AppCompatActivity() {

    private lateinit var choosedDate: String
    private lateinit var extraFlavor: String
    private lateinit var extraPrice: String
    private lateinit var extraQuantity: String
    private lateinit var binding: ActivityDateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraDate = intent?.extras

        binding.totalPrice.text = extraDate?.getString("chave_preco")

        binding.nextBtn.setOnClickListener {
            processingData(binding, extraDate)
            savingDataInBundle(binding, choosedDate, extraDate)
            nextScreen(binding, choosedDate, extraPrice, extraQuantity, extraFlavor)

        }
        binding.cancelDate.setOnClickListener {
            backScreen(binding)
        }

    }

    private fun nextScreen(
        binding: ActivityDateBinding,
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

    private fun backScreen(binding: ActivityDateBinding) {
        val intentCancelFlavor = Intent(this, MainActivity::class.java)
        startActivity(intentCancelFlavor)
        finish()
    }

    private fun recebeExtras(binding: ActivityDateBinding) {
        /** recebendo o dado quantidade da primeira tela*/
        val extraDate = intent?.extras
        processingData(binding, extraDate)
    }

    private fun processingData(binding: ActivityDateBinding, extrasData: Bundle?) {
        val choosedDateStr = when (binding.dateOptions.checkedRadioButtonId) {
            R.id.monday -> R.string.monday
            R.id.tuesday -> R.string.tuesday
            R.id.wednesday -> R.string.wednesday
            R.id.thursday -> R.string.thursday
            else -> R.string.friday

        }
        savingDataInBundle(binding, choosedDateStr.toString() /** corrigir para n precisar do toString()**/, extrasData)
    }

    private fun savingDataInBundle(binding: ActivityDateBinding, choosedDateStr: String, extrasData: Bundle?) {

        choosedDate = choosedDateStr
        extraPrice = extrasData?.getString("chave_preco").toString()
        extraQuantity = extrasData?.getString("chave_quantidade").toString()
        extraFlavor = extrasData?.getString("chave_sabor").toString()
        nextScreen(binding, choosedDate, extraPrice, extraQuantity,extraFlavor)
    }
}