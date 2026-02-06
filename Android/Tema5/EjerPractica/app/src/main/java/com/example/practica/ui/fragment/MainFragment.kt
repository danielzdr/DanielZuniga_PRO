package com.example.practica.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.practica.adapter.AdapterUser
import com.example.practica.databinding.FragmentMainBinding
import com.example.practica.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import org.json.JSONArray

class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var uid:String
    private lateinit var database: FirebaseDatabase
    private lateinit var adapterUser: AdapterUser
    private val urlBase = "https://dummyjson.com/users"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapterUser= AdapterUser(context)
        auth= FirebaseAuth.getInstance()
        uid=auth.currentUser!!.uid
        database= FirebaseDatabase.getInstance("https://dzrces2526-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainBinding.inflate(inflater,container,false)
        binding.recyclerView.adapter=adapterUser
        binding.recyclerView.layoutManager=
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        realizarPeticionJSON()
        return binding.root
    }

    private fun realizarPeticionJSON() {
        val peticionJSON: JsonObjectRequest= JsonObjectRequest(urlBase, {
            Log.v("datos", "llega")
            val gson = Gson()
            val usersArray: JSONArray = it.getJSONArray("usuarios")
            for (i in 0..usersArray.length() - 1) {
                val userJSON = usersArray.getJSONObject(i)
                val user: Usuario = gson.fromJson(userJSON.toString(), Usuario::class.java)
                adapterUser.addUsuario(user)
            }
        }, {
            Log.v("datos", "error")
        })
        Volley.newRequestQueue(this).add(peticionJSON)
    }

    override fun onResume() {
        super.onResume()
        binding.nombreTextView.text=uid
        binding.guardarDatos.setOnClickListener {
            val referencia=database.reference.child("practica").child(uid)
                .child("usuarios").child("nombre").push()
            referencia.setValue("App de practica")
        }
        binding.eliminarDatos.setOnClickListener {
            val  referencia=database.reference.child("practica")
                referencia.setValue(null)
        }
    }
}