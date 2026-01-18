package com.example.agendajson

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.agendajson.adapter.AdapterUser
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.dialogos.DialogoDetallesUsuario
import com.example.agendajson.dialogos.DialogoFiltrar
import com.example.agendajson.model.User
import com.google.gson.Gson
import org.json.JSONArray

class MainActivity : AppCompatActivity(), DialogoFiltrar.OnGeneroSeleccionadoListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdapterUser
    private var listaCompletaUsuarios = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        initGUI()
        realizarPeticionJSON()
    }

    private fun instancias() {

        adapter = AdapterUser(this)
        adapter.setOnItemClickListener { usuario ->
            // Esto se ejecutará cuando se haga clic en cualquier usuario
            mostrarDialogoDetalles(usuario)
        }
    }

    private fun mostrarDialogoDetalles(usuario: User) {
        // Usar tu DialogoDetallesUsuario
        val dialogo = DialogoDetallesUsuario.newInstance(usuario)
        dialogo.show(supportFragmentManager, "detalles_usuario")
    }

    private fun initGUI() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerUsers.adapter = adapter
        binding.recyclerUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun realizarPeticionJSON() {
        val url = "https://dummyjson.com/users"
        val peticionJSON: JsonObjectRequest = JsonObjectRequest(
            url,
            { response ->
                val gson = Gson()
                val usersArray: JSONArray = response.getJSONArray("users")


                adapter.clearUsers()
                listaCompletaUsuarios.clear()

                for (i in 0 .. usersArray.length()-1) {
                    val userJSON = usersArray.getJSONObject(i)
                    val user: User = gson.fromJson(
                        userJSON.toString(),
                        User::class.java
                    )
                    adapter.addUser(user)
                    listaCompletaUsuarios.add(user) // Guardar copia
                }

                supportActionBar?.title = "Usuarios (${adapter.itemCount})"
            },
            { error ->
                Log.e("conexion", "Error en la conexion: ${error.message}")
                Toast.makeText(this, "Error al cargar usuarios", Toast.LENGTH_SHORT).show()
            })

        Volley.newRequestQueue(this).add(peticionJSON)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_filtrar -> {
                val dialogoFiltrar = DialogoFiltrar()
                dialogoFiltrar.show(supportFragmentManager, "DialogoFiltrar")
                return true
            }
            R.id.menu_eliminar -> {
                // Mostrar todos los usuarios
                onGeneroSeleccionado("Todos")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onGeneroSeleccionado(genero: String) {
        Log.d("FILTRO", "Filtrando por: $genero")


        adapter.clearUsers()

        if (genero == "Todos") {

            for (user in listaCompletaUsuarios) {
                adapter.addUser(user)
            }
        } else {
            // Determinar el valor del género para la API
            val valorGenero = when (genero) {
                "Masculino" -> "masculino"
                "Femenino" -> "femenino"
                else -> genero
            }

            // Filtrar los usuarios
            for (user in listaCompletaUsuarios) {
                if (user.gender.equals(valorGenero, ignoreCase = true)) {
                    adapter.addUser(user)
                }
            }
        }


        supportActionBar?.title = "Usuarios (${adapter.itemCount}) - $genero"
        Toast.makeText(this, "Filtrado: $genero", Toast.LENGTH_SHORT).show()
    }
}

