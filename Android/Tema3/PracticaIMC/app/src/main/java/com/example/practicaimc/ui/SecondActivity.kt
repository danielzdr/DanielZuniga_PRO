package com.example.practicaimc.ui

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaimc.R
import com.example.practicaimc.databinding.ActivitySecondBinding
import com.example.practicaimc.model.Hombres
import com.example.practicaimc.model.Mujeres

class SecondActivity: AppCompatActivity(), RadioGroup.OnCheckedChangeListener{
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }

    private fun acciones() {
        val persona = intent.getSerializableExtra("PERSONA")

        if (persona is Hombres) {
            binding.editText1.setText(persona.peso.toString())
            binding.editText2.setText(persona.altura.toString())
            binding.radioMasculino.isChecked=true
            binding.radiofemenino.isChecked=false
            val imc = persona.calcularIMCHombres()
            val estado = persona.calcularEstadoHombres()
            binding.resultadoImc.text = "Tu IMC es: %.2f".format(imc)
            binding.estado.text = estado
            val imagen = when (estado) {
                "Bajo peso" -> R.drawable.estado1
                "Peso normal" -> R.drawable.estado2
                "Sobrepeso" -> R.drawable.estado3
                "Obesidad clase 1" -> R.drawable.estado4
                "Obesidad clase 2" -> R.drawable.estado5
                else -> R.drawable.estado6
            }
            binding.imagenEstado.setImageResource(imagen)

        } else if (persona is Mujeres) {
            binding.editText1.setText(persona.peso.toString())
            binding.editText2.setText(persona.altura.toString())
            binding.radioMasculino.isChecked=false
            binding.radiofemenino.isChecked=true
            val imc = persona.calcularIMCMujeres()
            val estado = persona.calcularEstadoMujeres()
            binding.resultadoImc.text = "Tu IMC es: %.2f".format(imc)
            binding.estado.text = estado
            val imagen = when (estado) {
                "Bajo peso" -> R.drawable.estado1
                "Peso normal" -> R.drawable.estado2
                "Sobrepeso" -> R.drawable.estado3
                "Obesidad clase 1" -> R.drawable.estado4
                "Obesidad clase 2" -> R.drawable.estado5
                else -> R.drawable.estado6
            }
            binding.imagenEstado.setImageResource(imagen)
        }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        binding.radioMasculino.isChecked=true
        binding.radiofemenino.isChecked=false
    }
}
