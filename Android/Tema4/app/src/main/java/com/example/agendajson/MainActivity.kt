package com.example.agendajson
import android.content.Intent
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
import com.example.agendajson.adapter.AdapterFav
import com.example.agendajson.adapter.AdapterUser
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.dialogos.DialogoDetallesUsuario
import com.example.agendajson.dialogos.DialogoFiltrar
import com.example.agendajson.model.User
import com.example.agendajson.ui.SecondActivity
import com.google.gson.Gson
import org.json.JSONArray

class MainActivity : AppCompatActivity(), DialogoFiltrar.OnGeneroSeleccionadoListener,
    AdapterUser.OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdapterUser
    private val urlBase = "https://dummyjson.com/users"
    private val listaFavoritos = arrayOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        initGUI()
        realizarPeticionJSON(urlBase)
    }

    private fun instancias() {

        adapter = AdapterUser(this)
    }
    private fun initGUI() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerUsers.adapter = adapter
        binding.recyclerUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun realizarPeticionJSON(url: String) {

        // 1. Realizar la peticion de forma correcta
        val peticionJSON: JsonObjectRequest = JsonObjectRequest(
            urlBase,
            {
                Log.v("datos","llega")

                val gson = Gson()
                val usersArray: JSONArray = it.getJSONArray("users")
                for (i in 0..usersArray.length() - 1) {
                    val userJSON = usersArray.getJSONObject(i)
                    val user: User = gson.fromJson(
                        userJSON.toString(),
                        User::class.java
                    )
                    adapter.addUser(user)
                }
            },
            {
                Log.v("conexion", "Error en la conexion")
            })
        // 2. Añado la peticion a la pila de Volley
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
                dialogoFiltrar.show(supportFragmentManager, null)
                return true
            }
            R.id.menu_ver_fav->{
                // Pasar la lista de favoritos a SecondActivity
                val intent = Intent(this, SecondActivity::class.java)
                if (listaFavoritos.isNotEmpty()) {
                    intent.putExtra("users_fav", arrayOf(listaFavoritos))
                } else {
                    Toast.makeText(this, "No hay favoritos aún", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    // PETICION JSON CON UNA URL ALGO DIFERENTE
    override fun onGeneroSeleccionado(genero: String) {
        adapter.clearUsers()
        if (genero == "Todos")
            realizarPeticionJSON(urlBase)
        else {
            val urlGender = "$urlBase/filter?key=gender&value=$genero"
            realizarPeticionJSON(urlGender)
        }
        Toast.makeText(this, "Filtrado: $genero", Toast.LENGTH_SHORT).show()
    }

    //llamar al diálogo de detalles del usuario desde el adaptador
    override fun onUserDetalles(user: User) {
        val dialogoDetalles = DialogoDetallesUsuario.newInstance(user)
        dialogoDetalles.show(supportFragmentManager, null)
    }


}

