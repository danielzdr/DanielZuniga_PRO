package com.example.holamundo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.holamundo.databinding.ActivityMainBinding
import android.widget.SearchView
import android.widget.SeekBar
import com.google.android.material.snackbar.Snackbar
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(), View.OnClickListener {

    //lateinit var boton:Botton
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //  solo esta línea

        Log.v("ciclo_vida", "Ejecutando metodo onCreate")
        acciones()
    }

    private fun acciones() {
        // Botón
        binding.botonSaludar?.setOnClickListener { view ->
            // Mostrar la imagen (más grande)
            binding.imagenPablo?.apply {
                setImageResource(R.drawable.img_1959)
                visibility = View.VISIBLE
                scaleX = 1.5f   // aumenta el tamaño horizontal (1.0 = normal)
                scaleY = 1.5f   // aumenta el tamaño vertical
            }

            // Snackbar con opción de ocultar imagen
            val snackbar = Snackbar.make(
                view,
                getString(R.string.saludo_snack),
                Snackbar.LENGTH_INDEFINITE
            )
            snackbar.setAction("Ocultar") {
                // Ocultar imagen al presionar "Ocultar"
                binding.imagenPablo?.visibility = View.GONE
                snackbar.dismiss()
            }
            snackbar.show()
        }
        binding.botonSaludar?.setBackgroundTintList(
            ContextCompat.getColorStateList(this, R.color.marronClaro)
        )

        // SearchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, "Buscando: $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Aquí puedes filtrar una lista, cambiar texto, etc.
                Log.d("SearchView", "Texto actual: $newText")
                return true
            }
        })

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Cuando el usuario empieza a mover el SeekBar
                Log.d("SeekBar", "Inicio del toque")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Cuando el usuario deja de mover el SeekBar
                Log.d("SeekBar", "Fin del toque, progresion final: ${seekBar?.progress}")
                Toast.makeText(this@MainActivity, "Barra de progresion: ${seekBar?.progress}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("ciclo_vida", "Ejecutando metodo onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.v("ciclo_vida", "Ejecutando metodo onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("ciclo_vida", "Ejecutando metodo onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("ciclo_vida", "Ejecutando metodo onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("ciclo_vida", "Ejecutando metodo onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("ciclo_vida", "Ejecutando metodo onDestroy")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}