package com.example.contador

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var contador:Int =0
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contador=savedInstanceState?.getInt("tag contador")?:0//operador elvis para numero
        binding.Cero.text=contador.toString()

        acciones()

    }

    private fun acciones(){
        binding.botonMas.setOnClickListener(this)
        binding.botonMenos.setOnClickListener(this)
        binding.botonReset?.setOnClickListener(this)
    }

    //para guardar mi contador
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("tag contador",contador)
    }
    override fun onClick(v: View?) {
        //diferenciar quien ha lanzado el evento
        //usamos when para ser mas productivo
        when(v!!.id){
            binding.botonMenos.id->{
                contador--
            }
            binding.botonMas.id->{
                contador++
            }
            binding.botonReset?.id->{
                contador=0
            }
        }
        binding.Cero.text = contador.toString()
    }

}