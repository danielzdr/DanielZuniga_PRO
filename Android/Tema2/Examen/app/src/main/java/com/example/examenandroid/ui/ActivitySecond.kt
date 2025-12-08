package com.example.examenandroid.ui

import android.R.drawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.examenandroid.databinding.ActivitySecondBinding
import com.example.examenandroid.model.Coche

class ActivitySecond : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var coche: Coche
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarDatos()

    }

    fun  cargarDatos(){

    }




    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}