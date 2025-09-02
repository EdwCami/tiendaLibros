package com.example.bookstore.Vendedor

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.bookstore.R
import com.example.bookstore.SeleccionarTipoActivity
import com.example.bookstore.Vendedor.Bottom_Nav_Fragments_Vendedor.FragmentMisProductosV
import com.example.bookstore.Vendedor.Bottom_Nav_Fragments_Vendedor.FragmentOrdenesV
import com.example.bookstore.Vendedor.Nav_Fragments_Vendedor.FragmentCategoriasV
import com.example.bookstore.Vendedor.Nav_Fragments_Vendedor.FragmentInicioV
import com.example.bookstore.Vendedor.Nav_Fragments_Vendedor.FragmentMiTiendaV
import com.example.bookstore.Vendedor.Nav_Fragments_Vendedor.FragmentReseniasV
import com.example.bookstore.databinding.ActivityMainVendedorBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivityVendedor : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding : ActivityMainVendedorBinding
    private var firebaseAuth : FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar  = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        firebaseAuth = FirebaseAuth.getInstance()
        comprobarSesion()

        binding.navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        replaceFragment(FragmentInicioV())
        binding.navigationView.setCheckedItem(R.id.op_inicio_V)

    }

    private fun cerrarSesion(){
        firebaseAuth!!.signOut()
        startActivity(Intent(applicationContext, SeleccionarTipoActivity::class.java))
        finish()
        Toast.makeText(applicationContext, "Has cerrado sesión", Toast.LENGTH_SHORT).show()
    }


    private fun comprobarSesion() {
        if (firebaseAuth!!.currentUser==null){
            startActivity(Intent(applicationContext, SeleccionarTipoActivity::class.java))
        }else{
            Toast.makeText(applicationContext, "Vendedor en linea", Toast.LENGTH_SHORT).show()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navFragment, fragment)
            .commit()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.op_inicio_V->{
                replaceFragment(FragmentInicioV())
            }
            R.id.op_mi_tienda_V->{
                replaceFragment(FragmentMiTiendaV())
            }
            R.id.op_categorias_v->{
                replaceFragment(FragmentCategoriasV())
            }
            R.id.op_resenia_V->{
                replaceFragment(FragmentReseniasV())
            }
            R.id.op_cerrar_sesion_V->{
                cerrarSesion()
            }
            R.id.op_mis_productos_V->{
                replaceFragment(FragmentMisProductosV())
            }
            R.id.op_mis_ordenes_V->{
                replaceFragment(FragmentOrdenesV())
            }
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}