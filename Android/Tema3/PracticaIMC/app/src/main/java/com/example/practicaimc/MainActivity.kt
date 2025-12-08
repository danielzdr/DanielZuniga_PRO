package com.example.practicaimc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaimc.databinding.ActivityMainBinding
import com.example.practicaimc.model.Hombres
import com.example.practicaimc.model.Mujeres
import com.example.practicaimc.ui.SecondActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()

        }

    private fun acciones() {
        binding.botonCalcular.setOnClickListener {
            val peso = binding.editText1.text.toString().toDoubleOrNull()
            val altura = binding.editText2.text.toString().toDoubleOrNull()

            if (peso == null || altura == null || binding.radioGroup.checkedRadioButtonId == -1) {
                Snackbar.make(binding.root, "Faltan datos", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val persona = if (binding.radiofemenino.isChecked) {
                Mujeres(peso, altura)
            } else {
                Hombres(peso, altura)
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("PERSONA", persona)
            startActivity(intent)
        }

    }


}