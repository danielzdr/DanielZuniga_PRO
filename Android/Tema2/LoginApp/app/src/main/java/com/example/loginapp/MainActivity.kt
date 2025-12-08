package com.example.loginapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.AdapterView
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapp.databinding.ActivityMainBinding
import com.example.loginapp.model.Usuario
import com.example.loginapp.ui.activity.SecondActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener,
    AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }

    private fun acciones() {

        binding.botonLogin.setOnClickListener(this)
        binding.gith.setOnClickListener(this)
        binding.google.setOnClickListener(this)
        binding.facebook.setOnClickListener(this)
        binding.checkRecordar.setOnCheckedChangeListener(this)
        binding.spinnerPerfil.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    binding.gith.visibility = View.INVISIBLE;
                    binding.google.visibility = View.INVISIBLE;
                    binding.facebook.visibility = View.INVISIBLE;

                    when (position) {
                        0 -> {

                            binding.gith.visibility = View.VISIBLE;
                        }

                        1 -> {
                            binding.facebook.visibility = View.VISIBLE;
                        }

                        2 -> {
                            binding.google.visibility = View.VISIBLE;
                        }
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.gith.id -> {}
            binding.facebook.id -> {}
            binding.google.id -> {}
            binding.botonLogin.id -> {

                if (binding.editPass.text.isNotEmpty() && binding.editCorreo.text.isNotEmpty()) {
                    if (binding.editPass.text.toString().equals("admin")
                        && binding.editCorreo.text.toString().equals("admin@admin.com", true)
                    ) {
                        val intent: Intent = Intent(
                            applicationContext,
                            SecondActivity::class.java
                        )

                        val usuario: Usuario = Usuario(binding.editCorreo.text.toString(),
                            binding.editPass.text.toString(),
                            binding.spinnerPerfil.selectedItem.toString())

                        intent.putExtra("usuario",usuario)
                        intent.putExtra("gitHub", R.drawable.gith)
                        intent.putExtra("facebook", R.drawable.facebook)
                        intent.putExtra("google",R.drawable.google)
                        // intent.putExtra("correo",binding.editCorreo.text.toString())
                        // intent.putExtra("pass",binding.editPass.text.toString())
                        // intent.putExtra("plataforma",binding.spinnerPerfil.selectedItem.toString())
                        startActivity(intent)
                    } else {
                        Snackbar.make(
                            binding.root, resources.getString(R.string.text_data),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                } else {
                    Snackbar.make(
                        binding.root, "Faltan datos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }


            }
        }
    }

    override fun onCheckedChanged(
        buttonView: CompoundButton,
        isChecked: Boolean
    ) {
        binding.botonLogin.isEnabled= isChecked
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