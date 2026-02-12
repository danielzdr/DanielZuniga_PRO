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
import com.example.practica.ui.fragment.dialog.DialogoDetalleUsuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import org.json.JSONArray

class MainFragment: Fragment(), AdapterUser.onUserClickListener {
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

        return binding.root
    }

    private fun realizarPeticionJSON() {
        val peticionJSON: JsonObjectRequest= JsonObjectRequest(urlBase, {
            Log.v("datos", "llega")
            val gson = Gson()
            val usersArray: JSONArray = it.getJSONArray("users")
            for (i in 0..usersArray.length() - 1) {
                val userJSON = usersArray.getJSONObject(i)
                val user: Usuario = gson.fromJson(userJSON.toString(), Usuario::class.java)
                adapterUser.addUsuario(user)
            }
        }, {
            Log.v("datos", "error")
        })
        Volley.newRequestQueue(requireContext()).add(peticionJSON)
    }

    override fun onResume() {
        super.onResume()
        val gson= Gson()
        database.reference.child("usuarios").child("usuario").addChildEventListener(
            object : ChildEventListener{
                override fun onChildAdded(
                    snapshot: DataSnapshot,
                    previousChildName: String?
                ) {
                    val usuario: Usuario=gson.fromJson(snapshot.value.toString(), Usuario::class.java)
                    Log.v("consulta", usuario.nombre.toString())
                }

                override fun onChildChanged(
                    snapshot: DataSnapshot,
                    previousChildName: String?
                ) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(
                    snapshot: DataSnapshot,
                    previousChildName: String?
                ) {

                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )

        binding.guardarDatos.setOnClickListener {
            realizarPeticionJSON()
            val referencia=database.reference.child("usuarioLogeado").child(auth.currentUser!!.uid)
            referencia.setValue("users")
            //referencia.setValue(Usuario("daniel","zuñiga",25, "daniel@gmail.com", "Ces12345a", 1))
        }
        binding.eliminarDatos.setOnClickListener {
            val  referencia=database.reference.child("usuarios").child(uid)
                referencia.setValue(null)
        }
    }

    override fun onUserDetalles(usuario: Usuario) {
      val dialogoDetalleUsuario= DialogoDetalleUsuario.newInstance(usuario)
        dialogoDetalleUsuario.show(requireFragmentManager(),null)
    }
}