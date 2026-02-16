package com.example.examen.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen.adapter.AdapterImagen
import com.example.examen.databinding.MainFragmentBinding
import com.example.examen.dataset.DataSet
import com.example.examen.model.Elemento
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class FragmentMain: Fragment() {
private lateinit var binding: MainFragmentBinding
private lateinit var auth: FirebaseAuth
private lateinit var database: FirebaseDatabase
private lateinit var AdapterImagen: AdapterImagen
private var email: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= FirebaseAuth.getInstance()
        AdapterImagen= AdapterImagen(context)
        database= FirebaseDatabase.getInstance("https://dzrces2526-default-rtdb.europe-west1.firebasedatabase.app/")
        email= arguments?.getString("email")

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
        AdapterImagen= AdapterImagen(requireContext())
        binding.recyclerViewMain.adapter= AdapterImagen
        binding.recyclerViewMain.layoutManager= LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,
            false)
        val gson = Gson()
        database.reference.child("elementos").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val json = gson.toJson(i.value)
                    val elemento = gson.fromJson(json, Elemento::class.java)
                    AdapterImagen.addElemento(elemento)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })



    }
}