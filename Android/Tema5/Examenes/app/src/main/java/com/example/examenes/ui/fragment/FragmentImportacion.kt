package com.example.examenes.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.examenes.R
import com.example.examenes.databinding.ImportacionFragmentBinding
import com.example.examenes.model.Examen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson

import com.google.gson.JsonArray
import org.json.JSONArray


class FragmentImportacion: Fragment() {
    private lateinit var binding: ImportacionFragmentBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private var importando: Boolean=false


    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance("https://dzrces2526-default-rtdb.europe-west1.firebasedatabase.app/")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ImportacionFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        realizarPeticionJSON()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        binding.btnImportar.setOnClickListener {
            // cuando se pulsa el boton de importar, se hace la peticion a la api y se guardan los datos en firebase
                    realizarPeticionJSON()

        }
    }

    fun realizarPeticionJSON() {
        val urlBase = "https://698949d3c04d974bc69ed0b0.mockapi.io/api/v1/examenes"
        val referenciaExamenes = database.getReference("examenes")

        val peticionJSON = JsonArrayRequest(
            Request.Method.GET,
            urlBase,
            null,
            { response ->
                Log.d("datos", "Llegan datos: ${response.length()}")
                try {
                    val gson = Gson()
                    for (i in 0 .. response.length()) {
                        val examenJSON = response.getJSONObject(i)
                        val examen = gson.fromJson(examenJSON.toString(), Examen::class.java)

                        Log.d("datos", "Procesando examen: ${examen.titulo}, ID: ${examen.id}")

                        // Usar el ID del examen como clave en Firebase
                        if (examen.id != null) {
                            referenciaExamenes.child(examen.id.toString())
                                .setValue(examen)
                                .addOnSuccessListener {
                                    Log.d("datos", "Examen ${examen.id} guardado correctamente")
                                }
                                .addOnFailureListener { e ->
                                    Log.e("datos", "Error al guardar examen ${examen.id}", e)
                                }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("datos", "Error procesando JSON", e)
                }
            },
            { error ->
                Log.e("datos", "Error en petición: ${error.message}")
            }
        )
        Volley.newRequestQueue(requireContext()).add(peticionJSON)
    }
}