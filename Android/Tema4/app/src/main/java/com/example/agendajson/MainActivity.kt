package com.example.agendajson

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.agendajson.adapter.AdapterUser
import com.example.agendajson.databinding.ActivityMainBinding
import com.example.agendajson.model.User
import com.google.gson.Gson
import org.json.JSONArray

class MainActivity : AppCompatActivity(), AdapterUser.OnUserListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUser: AdapterUser
    private lateinit var listaCompleta: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        realizarPeticionJson()
        val listaUser=ArrayList<User>()
        adapterUser= AdapterUser(listaUser ,this)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.recyclerUsers.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        } else {
            binding.recyclerUsers.layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(this, 2, androidx.recyclerview.widget.GridLayoutManager.VERTICAL, false)
        }

        binding.recyclerUsers.adapter=adapterUser

    }

    private fun cargarUsers(){
        val url= "https://dummyjson.com/users"
        val request= JsonObjectRequest(url,
            {
                response->
                val  usersArray=response.getJSONArray("users")
                val gson= Gson()
                for (i in 0 .. usersArray.length()-1) {
                    val userJSON = usersArray.getJSONObject(i)
                    val user: User = gson.fromJson(
                        userJSON.toString(),
                        User::class.java)
                    listaCompleta.add(user)
                }
                adapterUser.setDatos(listaCompleta)
            },
            {
                error ->
                Log.v("conexion","Error al obtener los datos: ${error.toString()}")
            })
    }


    private fun realizarPeticionJson() {
        val url= "https://dummyjson.com/users"
        //1. Realizar la peticion de forma correcta
        val peticionJson: JsonObjectRequest= JsonObjectRequest(url,
            {
                val gson= Gson()
                val usersArray: JSONArray= it.getJSONArray("users")
                for (i in 0 .. usersArray.length()-1){
                    val userJSON=usersArray.getJSONObject(i)
                    val user :User =gson.fromJson(userJSON.toString(),
                        User::class.java)
                    Log.v("conexion","El nombre del usuario es ${user.firstName}")
                }
                Log.v("conexion",usersArray.toString())
            },
            {
                Log.v("conexion","Error al obtener los datos")
            })
        //2.Añado la peticion a la pila de Volley
        Volley.newRequestQueue(this).add(peticionJson)



    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
        R.id.menu_filtrar->{

        }
        R.id.menu_eliminar->{

        }
    }
        return super.onOptionsItemSelected(item)
    }

    override fun onUserSeleccionado(user: User) {

    }
}