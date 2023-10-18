package com.example.cupcakeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cupcakeapp.databinding.ActivityFlavorBinding

class FlavorActivity : AppCompatActivity() {

    private lateinit var choosedFlavor: String
    private lateinit var extraPrice: String
    private lateinit var extraQuantity: String
    private lateinit var binding: ActivityFlavorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFlavorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** recebendo os extras da tela anterior*/
        val extraFlavor = intent?.extras
        extraPrice = extraFlavor?.getString("chave_preco").toString()

        binding.totalPrice.text = extraPrice

        binding.nextBtn.setOnClickListener {
            processingData(binding, extraFlavor)
            savingDataInBundle(binding, choosedFlavor, extraFlavor)
            nextScreen(binding, choosedFlavor, extraPrice, extraQuantity)
        }

        binding.cancelFlavor.setOnClickListener {
            backScreen(binding)
        }
    }
    private fun nextScreen(
        binding: ActivityFlavorBinding,
        choosedFlavor: String,
        extraPrice: String,
        extraQuantity: String
    ){
        val intent = Intent(this, DateActivity::class.java)
        intent.putExtra("chave_sabor", choosedFlavor)
        intent.putExtra("chave_preco", extraPrice)
        intent.putExtra("chave_quantidade", extraQuantity)
        startActivity(intent)
    }
    private fun backScreen(binding: ActivityFlavorBinding){
        val intentCancelFlavor = Intent(this, MainActivity::class.java)
        startActivity(intentCancelFlavor)
        finish()
    }

    private fun receveingData(binding: ActivityFlavorBinding){
        /** recebendo o dado quantidade da primeira tela*/
        val extrasSabor = intent?.extras
        processingData(binding, extrasSabor)
    }
    private fun processingData(binding: ActivityFlavorBinding, extrasSabor: Bundle?){
        val choosedFlavorStr = when (binding.flavorOptions.checkedRadioButtonId){
            R.id.flavor_strawberry-> "Morango"
            R.id.flavor_chocholate -> "Chocolate"
            R.id.flavor_coffe -> "Café"
            R.id.flavor_redvelvet -> "Red Velvet"
            else -> "Caramelo"
        }
        savingDataInBundle(binding, choosedFlavorStr, extrasSabor)
    }

    private fun savingDataInBundle(
        binding: ActivityFlavorBinding,
        ChoosedFlavorStr: String,
        extraFlavor: Bundle?
    ){
        choosedFlavor = ChoosedFlavorStr
        extraPrice = extraFlavor?.getString("chave_preco").toString()
        extraQuantity = extraFlavor?.getString("chave_quantidade").toString()
        

        nextScreen(binding, choosedFlavor, extraPrice, extraQuantity)
    }
}