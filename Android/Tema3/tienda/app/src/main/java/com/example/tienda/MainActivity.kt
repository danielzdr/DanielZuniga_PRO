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
import com.example.tienda.ui.dialogs.DialogoInformacion
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),
    AdapterProducto.OnProductoCarritoListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterProducto: AdapterProducto
    private var filtroAplicado: Boolean = false

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
            val intent = Intent(this, ProductosActivity::class.java)
            startActivity(intent)
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
        when(item.itemId){
            R.id.menu_carrito -> {
                // Navegar a la actividad del carrito
                val intent = Intent(this, ProductosActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_filtrar -> {
                // Filtrar por una categoría específica (ejemplo: "tecnologia")
                val listaFiltrada = DataSet.getListaFiltrada("tecnologia")
                adapterProducto.chageList(listaFiltrada)
                filtroAplicado = true
                return true
            }
            R.id.menu_limpiar -> {
                // Quitar filtro y mostrar todos los productos
                val listaCompleta = DataSet.lista
                adapterProducto.chageList(listaCompleta)
                filtroAplicado = false
                binding.spinnerCategorias.setSelection(0) // Volver a "Todas"
                return true
            }
            R.id.menu_info->{
                val dialogoInfromacion: DialogoInformacion= DialogoInformacion()
                //el tag que me da el show es para diferenciar entre los diferentes fragments
                dialogoInfromacion.show(supportFragmentManager,null)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun actualizarContadorCarrito() {
        binding.contadorCarrito.text = DataSet.listaCarrito.size.toString()
    }
}