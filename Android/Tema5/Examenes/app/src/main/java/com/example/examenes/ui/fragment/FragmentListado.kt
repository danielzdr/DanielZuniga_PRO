package com.example.examenes.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenes.adapter.AdapterExamen
import com.example.examenes.databinding.ListadoFragmentBinding

class FragmentListado: Fragment() {
    private lateinit var binding: ListadoFragmentBinding
    private lateinit var adapter: AdapterExamen
    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter= AdapterExamen(context)

    }
        override fun onCreateView(
            inflater: android.view.LayoutInflater,
            container: android.view.ViewGroup?,
            savedInstanceState: android.os.Bundle?
        ): android.view.View? {
            binding= ListadoFragmentBinding.inflate(inflater,container,false)
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= AdapterExamen(requireContext())
        binding.recyclerView.adapter=adapter
         binding.recyclerView.layoutManager=
             LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }
    override fun onResume() {
        super.onResume()
    }
}