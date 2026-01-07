package com.example.tienda

import android.R.layout
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.service.autofill.Dataset
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.databinding.ActivityMainBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto
import com.example.tienda.ui.activitys.ProductosActivity
import com.example.tienda.ui.activitys.SecondActivity
import com.example.tienda.ui.dialogs.DialogoCarrito
import com.example.tienda.ui.dialogs.DialogoComparar
import com.example.tienda.ui.dialogs.DialogoInformacion
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),
    AdapterProducto.OnProductoCarritoListener, DialogoComparar.OnCompararListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterProducto: AdapterProducto
    private var filtroAplicado: Boolean = false
    private var producto1: Producto? = null
    private var producto2: Producto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val lista: ArrayList<Producto> = DataSet.lista
        adapterProducto = AdapterProducto(lista, this)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerProductos.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else {
            binding.recyclerProductos.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        }

        binding.recyclerProductos.adapter = adapterProducto
        acciones()
        actualizarContadorCarrito() // Actualizar contador al iniciar
    }

    fun acciones() {
        binding.botonCarrito.setOnClickListener {
            val dialogoCarrito: DialogoCarrito = DialogoCarrito()
            dialogoCarrito.show(supportFragmentManager, null)
            //val intent = Intent(this, ProductosActivity::class.java)
            //startActivity(intent)
        }
        binding.spinnerCategorias.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    var categoriaSeleccionada = parent!!.adapter.getItem(position)
                    var listaFiltrada = DataSet.getListaFiltrada(categoriaSeleccionada.toString())
                    adapterProducto.chageList(listaFiltrada)
                    filtroAplicado = (categoriaSeleccionada != "Todas")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_carrito -> {
                val dialogoCarrito: DialogoCarrito = DialogoCarrito()
                dialogoCarrito.show(supportFragmentManager, null)
                // Navegar a la actividad del carrito
                /*val intent = Intent(this, ProductosActivity::class.java)
                startActivity(intent)
                return true*/
            }

            R.id.menu_filtrar -> {
                // Filtrar por una categoría específica (muebles, tecnologa, ropa o todas)
                val seleccionSpinner = binding.spinnerCategorias.selectedItem.toString()
                val lista = DataSet.getListaFiltrada(seleccionSpinner)
                adapterProducto.chageList(lista)
            }

            R.id.menu_limpiar -> {
                // Quitar filtro y mostrar todos los productos
                val lista = DataSet.getListaFiltrada("todas")
                adapterProducto.chageList(lista)
            }

            R.id.menu_info -> {
                val dialogoInformacion: DialogoInformacion = DialogoInformacion()
                //el tag que me da el show es para diferenciar entre los diferentes fragments
                dialogoInformacion.show(supportFragmentManager, null)
            }

            R.id.menu_comparar -> {
                // las variables producto1 y producto2 les asigne los productos seleccionados para comparar
                if (producto1 != null && producto2 != null) {
                    val dialogoComparar = DialogoComparar()
                    dialogoComparar.show(supportFragmentManager, null)
                } else {
                    Snackbar.make(
                        binding.root,
                        "Selecciona dos productos para comparar",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRestart() {
        super.onRestart()
        actualizarContadorCarrito()
    }

    override fun actualizarContadorCarrito() {
        binding.contadorCarrito.text = DataSet.listaCarrito.size.toString()
    }

    override fun onProductoSeleccionado(producto: Producto) {
        when {
            producto1 == null -> {
                producto1 = producto
                Snackbar.make(binding.root, "Producto 1 seleccionado", Snackbar.LENGTH_SHORT).show()
            }
            producto2 == null -> {
                producto2 = producto
                Snackbar.make(binding.root, "Producto 2 seleccionado", Snackbar.LENGTH_SHORT).show()
            }
            else -> {
                Snackbar.make(binding.root, "Ya has seleccionado 2 productos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCompararListener(opcion: String) {
        Snackbar.make(binding.root,
            "Comparar por: $opcion",
            Snackbar.LENGTH_SHORT).show()

        val p1 = producto1!!
        val p2 = producto2!!

        val resultado = when (opcion) {
            "Precio" -> {
                when {
                    p1.precio > p2.precio -> "${p1.nombre} es más caro"
                    p1.precio < p2.precio -> "${p2.nombre} es más caro"
                    else -> "Ambos productos tienen el mismo precio"
                }
            }

            "Categoria" -> {
                if (p1.categoria == p2.categoria)
                    "Ambos son de la misma categoría"
                else
                    "Son de categorías diferentes"
            }

            "Stock" -> {
                when {
                    p1.stock > p2.stock -> "${p1.nombre} tiene más stock"
                    p1.stock < p2.stock -> "${p2.nombre} tiene más stock"
                    else -> "Ambos tienen el mismo stock"
                }
            }

            else -> "Opción no válida"
        }

        mostrarResultado(resultado)
    }

    private fun mostrarResultado(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Resultado de la comparación")
            .setMessage(mensaje)
            .setPositiveButton("OK", null)
            .show()
    }


}