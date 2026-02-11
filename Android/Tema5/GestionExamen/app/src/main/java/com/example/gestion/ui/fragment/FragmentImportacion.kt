package com.example.gestion.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.gestion.R
import com.example.gestion.databinding.ImportacionFragmentBinding
import com.example.gestion.model.Examen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import org.json.JSONArray
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FragmentImportacion : Fragment() {
    private lateinit var binding: ImportacionFragmentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String
    private lateinit var database: FirebaseDatabase


    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser!!.uid
        database =
            FirebaseDatabase.getInstance("https://dzrces2526-default-rtdb.europe-west1.firebasedatabase.app/")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImportacionFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val referenciaExamenes = database.getReference("examenes").child(uid)

        referenciaExamenes.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    findNavController().navigate(R.id.action_fraagmentImportacion_to_dialogoError)
                } else {
                    realizarPeticionJSON()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("firebase", error.message)
            }
        })
    }

    private fun realizarPeticionJSON() {

        val urlBase = "https://698949d3c04d974bc69ed0b0.mockapi.io/api/v1/examenes"

        val referenciaExamen = database.getReference("examenes").child(uid)

        val peticionJSON = com.android.volley.toolbox.JsonArrayRequest(
            urlBase,
            { response ->

                Log.v("datos", "llega")

                val gson = Gson()

                for (i in 0 .. response.length()) {
                    val examenJSON = response.getJSONObject(i)
                    val exam = gson.fromJson(examenJSON.toString(), Examen::class.java)
                    referenciaExamen.child(exam.id.toString()).setValue(exam)
                }

                findNavController().navigate(R.id.action_fraagmentImportacion_to_dialogoOkey)

            },
            { error ->
                Log.v("datos", "error: ${error.message}")
            }
        )

        Volley.newRequestQueue(requireContext()).add(peticionJSON)
    }
}


