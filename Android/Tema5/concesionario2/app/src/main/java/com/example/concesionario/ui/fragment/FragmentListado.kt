package com.example.concesionario.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.concesionario.adapter.AdapterCoche
import com.example.concesionario.databinding.ListadoFragmentBinding
import com.example.concesionario.model.Coche
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class FragmentListado: Fragment() {
    private lateinit var binding: ListadoFragmentBinding
    private lateinit var adapterCoche: AdapterCoche
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapterCoche= AdapterCoche(context)
        auth= FirebaseAuth.getInstance()
        uid= auth.currentUser?.uid.toString()
        database= FirebaseDatabase.getInstance("https://dzrces2526-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ListadoFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.adapter=adapterCoche
        binding.recyclerView.layoutManager= LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL,false
        )
        val gson: Gson= Gson()
        database.getReference("coches").addValueEventListener(
            object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children){
                        val coche= gson.fromJson(i.value.toString(), Coche::class.java)
                        adapterCoche.addCoche(coche)
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            }
        )
            }
        }

