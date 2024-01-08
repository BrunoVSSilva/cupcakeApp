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
    private var extraQuantity: Int? = null
    private lateinit var binding: ActivityDateBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        extraDate = intent?.extras

        with(binding) {
            totalPrice.text = extraDate?.getString("chave_preco")
            nextBtn.setOnClickListener {
                nextScreen(chooseDate(), extraPrice, extraQuantity, extraFlavor)
            }
            cancelDate.setOnClickListener {
                backScreen()
            }
        }
    }

    private fun backScreen() {
        val intentCancelFlavor = Intent(this, MainActivity::class.java)
        startActivity(intentCancelFlavor)
        finish()
    }

    private fun chooseDate(): String {
        return when (binding.dateOptions.checkedRadioButtonId) {
            R.id.monday -> R.string.monday
            R.id.tuesday -> R.string.tuesday
            R.id.wednesday -> R.string.wednesday
            R.id.thursday -> R.string.thursday
            R.id.friday -> R.string.friday
            else -> null
        }.toString()
    }

    private fun nextScreen(
        choosedDate: String?,
        extraPrice: String?,
        extraQuantity: Int?,
        extraFlavor: String?
    ) {
        val intent = Intent(this, OrderSumarryActivity::class.java)
        with(intent) {
            putExtra("chave_data", choosedDate)
            putExtra("chave_preco", extraPrice)
            putExtra("chave_quantidade", extraQuantity)
            putExtra("chave_sabor", extraFlavor)
        }
        Log.d("testeData", "$choosedDate")
        startActivity(intent)
    }
}