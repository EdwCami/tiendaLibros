package com.example.bookstore.Vendedor.Nav_Fragments_Vendedor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiContext
import com.example.bookstore.R
import com.example.bookstore.Vendedor.Bottom_Nav_Fragments_Vendedor.FragmentMisProductosV
import com.example.bookstore.Vendedor.Bottom_Nav_Fragments_Vendedor.FragmentOrdenesV
import com.example.bookstore.Vendedor.Productos.AgregarProductosActivity
import com.example.bookstore.databinding.FragmentInicioVBinding

class FragmentInicioV : Fragment() {

    private lateinit var binding: FragmentInicioVBinding
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        mContext = context
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentInicioVBinding.inflate(inflater, container, false)

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.op_mis_productos_V->{
                    replaceFragment(FragmentMisProductosV())
                }
                R.id.op_mis_ordenes_V->{
                    replaceFragment(FragmentOrdenesV())
                }
            }
            true
        }

        replaceFragment(FragmentMisProductosV())
        binding.bottomNavigation.selectedItemId = R.id.op_mis_productos_V

        binding.addFab.setOnClickListener {
           startActivity(Intent(context, AgregarProductosActivity::class.java))

        }

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.bottomFragment, fragment)
            .commit()

    }

}