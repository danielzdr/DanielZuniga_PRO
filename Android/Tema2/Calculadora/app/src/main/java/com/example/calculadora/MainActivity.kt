package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    var operacion: TextView? = null
    var operador1: Double = 0.0
    var operador2: Double = 0.0
    var operacionActual: String = ""
    var operacionPendiente: String = ""
    var limpiarPantalla: Boolean = false
    var concatenacion: String=""
    private var currentValue: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        acciones()
        instancias()
        initGui()
        operacion = findViewById(R.id.textView)


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentValue", binding.textView.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentValue = savedInstanceState.getString("currentValue", "0")
        binding.textView.text = currentValue
    }

    private fun initGui() {
        binding.textView.text = "0"
    }

    private fun instancias() {

    }

    fun acciones() {
        binding.botonUno.setOnClickListener(this)
        binding.botonDos.setOnClickListener(this)
        binding.botonTres.setOnClickListener(this)
        binding.botonCuatro.setOnClickListener(this)
        binding.botonCinco.setOnClickListener(this)
        binding.botonSeis.setOnClickListener(this)
        binding.botonSiete.setOnClickListener(this)
        binding.botonOcho.setOnClickListener(this)
        binding.botonNueve.setOnClickListener(this)
        binding.botonMas.setOnClickListener(this)
        binding.botonMenos.setOnClickListener(this)
        binding.botonIgual.setOnClickListener(this)
        binding.botonMulti.setOnClickListener(this)
        binding.botonDivision.setOnClickListener(this)
        binding.botonPunto.setOnClickListener(this)
        binding.botonReset.setOnClickListener(this)
        binding.botonPorcentaje.setOnClickListener(this)
        binding.botonBorrar.setOnClickListener(this)



    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.botonUno -> agregarNumero("1")
            R.id.botonDos -> agregarNumero("2")
            R.id.botonTres -> agregarNumero("3")
            R.id.botonCuatro -> agregarNumero("4")
            R.id.botonCinco -> agregarNumero("5")
            R.id.botonSeis -> agregarNumero("6")
            R.id.botonSiete -> agregarNumero("7")
            R.id.botonOcho -> agregarNumero("8")
            R.id.botonNueve -> agregarNumero("9")
            R.id.botonCero -> agregarNumero("0")
            R.id.botonPunto -> agregarDecimal()
            R.id.botonMas -> establecerOperacion("+")
            R.id.botonMenos -> establecerOperacion("-")
            R.id.botonDivision -> establecerOperacion("/")
            R.id.botonMulti -> establecerOperacion("x")
            R.id.botonPorcentaje -> calcularPorcentaje()
            R.id.botonIgual -> calcularResultado()
            R.id.botonReset -> limpiarTodo()
            R.id.botonBorrar -> limpiarTodo()
            R.id.botonPI->agregarPi()
            R.id.botonRaiz -> calcularRaiz()
            R.id.botonLog -> calcularLogaritmo()
            R.id.botonCos -> calcularCoseno()
            R.id.botonEx -> agregarE()
        }
    }

    private fun limpiarTodo() {
        binding.textView.text = "0"
        operador1 = 0.0
        operador2 = 0.0
        operacionPendiente = ""
        limpiarPantalla = false
    }

    private fun agregarNumero(numero: String) {
        if (limpiarPantalla) {
            binding.textView.text = numero
            limpiarPantalla = false
            if (operacionPendiente.isNotEmpty()) {
                concatenacion += " $numero"
            } else {
                concatenacion = numero
            }
        } else {
            if (binding.textView.text == "0") {
                binding.textView.text = numero
            } else {
                binding.textView.text = "${binding.textView.text}$numero"
                if (operacionPendiente.isNotEmpty()) {
                    // Reemplazar el último número en la expresión
                    val partes = concatenacion.split(" ")
                    if (partes.size >= 2) {
                        concatenacion = "${partes[0]} ${partes[1]} $numero"
                    } else {
                        concatenacion += numero
                    }
                } else {
                    concatenacion += numero
                }
            }
        }
    }

    private fun agregarDecimal() {
        if (limpiarPantalla) {
            binding.textView.text = "0."
            limpiarPantalla = false
        } else {
            if (!binding.textView.text.contains(".")) {
                binding.textView.text = "${binding.textView.text}."
            }
        }
    }

    private fun establecerOperacion(operacion: String) {
        if (!limpiarPantalla) {
            calcularResultado()
        }
        operador1 = binding.textView.text.toString().toDouble()
        operacionPendiente = operacion
        limpiarPantalla = true
    }

    private fun calcularPorcentaje() {
        val valorActual = binding.textView.text.toString().toDouble()
        val resultado = valorActual / 100.0
        if (resultado % 1 == 0.0) {
            binding.textView.text = resultado.toInt().toString()
        } else {
            binding.textView.text = resultado.toString()
        }
        limpiarPantalla = true
    }

    private fun calcularResultado() {
        if (operacionPendiente.isNotEmpty() && !limpiarPantalla) {
            operador2 = binding.textView.text.toString().toDouble()
            var resultado = 0.0
            when (operacionPendiente) {
                "+" -> resultado = operador1 + operador2
                "-" -> resultado = operador1 - operador2
                "x" -> resultado = operador1 * operador2
                "/" -> {
                    if (operador1 != 0.0) {
                        resultado = operador1 / operador2
                    } else {
                        binding.textView.text = "Error"
                        operacionPendiente = ""
                        limpiarPantalla = true
                        return
                    }
                }
            }
            //Mostrar resultado sin decimales si es entero el numero
            if (resultado %1==0.0){
                binding.textView.text=resultado.toInt().toString()
            }else{
                binding.textView.text=resultado.toString()
            }
            operacionPendiente=""
            limpiarPantalla=true

        }

    }

    private fun agregarPi() {
        binding.textView.text = Math.PI.toString()
        limpiarPantalla = true
    }

    private fun agregarE() {
        binding.textView.text = Math.E.toString()
        limpiarPantalla = true
    }

    private fun calcularRaiz() {
        try {
            val valor = binding.textView.text.toString().toDouble()
            if (valor >= 0) {
                val resultado = Math.sqrt(valor)
                mostrarResultado(resultado)
            } else {
                binding.textView.text = "Error"
            }
        } catch (e: Exception) {
            binding.textView.text = "Error"
        }
    }

    private fun calcularLogaritmo() {
        try {
            val valor = binding.textView.text.toString().toDouble()
            if (valor > 0) {
                val resultado = Math.log10(valor)
                mostrarResultado(resultado)
            } else {
                binding.textView.text = "Error"
            }
        } catch (e: Exception) {
            binding.textView.text = "Error"
        }
    }

    private fun calcularCoseno() {
        try {
            val valor = binding.textView.text.toString().toDouble()
            val resultado = Math.cos(Math.toRadians(valor))
            mostrarResultado(resultado)
        } catch (e: Exception) {
            binding.textView.text = "Error"
            print(e.message)
        }
    }

    private fun mostrarResultado(resultado: Double) {
        if (resultado % 1 == 0.0) {
            binding.textView.text = resultado.toInt().toString()
        } else {
            binding.textView.text = String.format("%.8f", resultado).trimEnd('0').trimEnd('.')
        }
        limpiarPantalla = true
    }
}