package com.example.concesionario.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.concesionario.R
import com.example.concesionario.adapter.AdapterModelos
import com.example.concesionario.databinding.ActivitySecondBinding
import com.example.concesionario.model.Marca
import com.example.concesionario.model.Vehiculo

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    lateinit var adapterModelos: AdapterModelos
    lateinit var listaModelos: ArrayList<Vehiculo>
    var vehiculo: String?=null
    var marca: Marca?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        instancias()
        recuperarDatos()
        initGUI()
        }

    private fun recuperarDatos() {
        vehiculo=intent.getStringExtra("vehiculo")
        marca=intent.getSerializableExtra("marca")as Marca
    }

    private fun initGUI() {
        binding.textoVehiculo.setText(vehiculo)
        binding.textoMarca.setText(marca?.nombre)
        binding.spinnerModelos.adapter=adapterModelos
    }

    private fun acciones() {

    }

    private fun instancias() {
        listaModelos=arrayListOf(
            Vehiculo(
                Marca("Fiat",R.drawable.fiat),
                "600",
                100,
                20000,
                "rojo",
                R.drawable.fiat),
            Vehiculo(
                Marca("Ford",R.drawable.ford),
                "600",
                100,
                20000,
                "rojo",
                R.drawable.ford),
        )
        adapterModelos= AdapterModelos(listaModelos,this)
    }
}
