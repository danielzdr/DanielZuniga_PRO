package com.example.examenandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examenandroid.databinding.ActivityMainBinding
import com.example.examenandroid.model.Coche
import com.example.examenandroid.ui.ActivitySecond
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var coche: Coche
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        acciones()
        instancias()
        initGui()
        }

    private fun initGui() {

    }

    private fun instancias() {

    }

    private fun acciones() {
        binding.editNombre.setOnClickListener(this)
        binding.editApellidos.setOnClickListener(this)
        binding.editAutonomia.setOnClickListener(this)
        binding.matriculaCoche.setOnClickListener(this)
        binding.anioMatriculacion.setOnClickListener(this)
        binding.botonAcceder.setOnClickListener {
                view: View ->
            if (binding.editNombre.text.isNotEmpty() && binding.editApellidos.text.isNotEmpty()
                && binding.matriculaCoche.text.isNotEmpty() && binding.editAutonomia.text.toString().isNotEmpty()
                && binding.anioMatriculacion.text.toString().isNotEmpty()){
                Snackbar.make(
                    view,
                    "Accediendo",
                    Snackbar.LENGTH_SHORT
                ).show()
                val intent:Intent= Intent(applicationContext, ActivitySecond::class.java)
                startActivity(intent)

            }else{
                Snackbar.make(
                    view,
                    "Faltan datos por rellenar",
                    Snackbar.LENGTH_SHORT).show()
            }
        }

    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        if (coche.comprobarTipodeGasolina()=="Hibrido" &&
            coche.comprobarTipodeGasolina() =="Electrico"){
            binding.anioMatriculacion.isEnabled=false
        }else if (coche.comprobarTipodeGasolina()=="Hibrido"){
            binding.editAutonomia.isEnabled=true
        }

    }

    override fun onClick(v: View?) {

    }
}
