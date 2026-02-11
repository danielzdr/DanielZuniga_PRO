package com.example.gestion.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestion.adapter.AdapterUser
import com.example.gestion.databinding.ListadoFragmentBinding

class FragmentListado: Fragment() {
private lateinit var binding: ListadoFragmentBinding
private lateinit var adapterUser: AdapterUser
    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapterUser= AdapterUser(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ListadoFragmentBinding.inflate(inflater,container,false)
        binding.rvDetalle.adapter=adapterUser
        binding.rvDetalle.layoutManager=
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterUser= AdapterUser(requireContext())


    }

    override fun onResume() {
        super.onResume()

    }
}