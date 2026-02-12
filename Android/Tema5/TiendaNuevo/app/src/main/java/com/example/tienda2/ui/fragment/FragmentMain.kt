package com.example.tienda2.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tienda2.adapter.AdapterUsuario
import com.example.tienda2.databinding.MainFragmentBinding
import com.example.tienda2.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class FragmentMain: Fragment() {
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapterUsuario: AdapterUsuario
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance("https://tienda2-9c8e7-default-rtdb.europe-west1.firebasedatabase.app/")
        uid= auth.currentUser?.uid.toString()
        adapterUsuario=AdapterUsuario(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= MainFragmentBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.adapter=adapterUsuario
        binding.recyclerView.layoutManager= LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL,false
        )
        val gson= Gson()
        database.reference.child("usuarios")
            .addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val user: Usuario= i .getValue(Usuario::class.java) as Usuario
                    adapterUsuario.addUser(user)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}