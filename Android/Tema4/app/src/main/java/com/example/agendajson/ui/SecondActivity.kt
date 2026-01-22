package com.example.agendajson.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendajson.adapter.AdapterFav
import com.example.agendajson.databinding.ActivitySecondBinding
import com.example.agendajson.dataset.DataSet
import com.example.agendajson.dialogos.DialogoDetallesUsuario
import com.example.agendajson.model.User

class SecondActivity : AppCompatActivity(), AdapterFav.OnUserClickListener {
    lateinit var binding: ActivitySecondBinding
    lateinit var adapter: AdapterFav

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        instancias()
        initGUI()
        recibirDatos()
    }

    private fun instancias() {
        adapter = AdapterFav(this)
    }

    private fun initGUI() {
        setSupportActionBar(binding.toolbarFavs)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerFavs.adapter = adapter
        binding.recyclerFavs.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun recibirDatos() {
        val listaFavoritos = intent.getSerializableExtra("users_fav") as? ArrayList<User>

        if (listaFavoritos != null && listaFavoritos.isNotEmpty()) {
            listaFavoritos.forEach { user ->
                DataSet.agregarUsersFavs(user)
                adapter.notifyDataSetChanged()
            }

        } else {
            Toast.makeText(this, "No se recibieron favoritos", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onUserDetalles(user: User) {
        val dialogoDetalles = DialogoDetallesUsuario.newInstance(user)
        dialogoDetalles.show(supportFragmentManager, null)
    }
}