package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cupcakeapp.databinding.ActivityFlavorBinding

class FlavorActivity : AppCompatActivity() {

    private var choosedFlavor: String? = null
    private var extraPrice: String? = null
    private var extraQuantity: String? = null
    private lateinit var binding: ActivityFlavorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFlavorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraFlavor = intent?.extras
        extraPrice = extraFlavor?.getString("chave_preco").toString()

        with(binding){
            totalPrice.text = extraPrice

            nextBtn.setOnClickListener {
                nextScreen(chooseFlavor(), extraPrice, extraQuantity)
            }

            cancelFlavor.setOnClickListener {
                backScreen()
            }
        }
    }

    private fun backScreen(){
        val intentCancelFlavor = Intent(this, MainActivity::class.java)
        startActivity(intentCancelFlavor)
        finish()
    }

    private fun chooseFlavor(): String{
        return when (binding.flavorOptions.checkedRadioButtonId){
            R.id.flavor_strawberry-> R.string.flavor_strawberry
            R.id.flavor_chocholate -> R.string.flavor_chocolate
            R.id.flavor_coffe -> R.string.flavor_coffe
            R.id.flavor_redvelvet -> R.string.flavor_redvelvet
            R.id.flavor_caramel -> R.string.flavor_caramel
            else -> null
        }.toString()
    }

    private fun nextScreen(
        choosedFlavor: String?,
        extraPrice: String?,
        extraQuantity: String?
    ){
        val intent = Intent(this, DateActivity::class.java)
        intent.putExtra("chave_sabor", choosedFlavor)
        intent.putExtra("chave_preco", extraPrice)
        intent.putExtra("chave_quantidade", extraQuantity)
        startActivity(intent)
    }
}