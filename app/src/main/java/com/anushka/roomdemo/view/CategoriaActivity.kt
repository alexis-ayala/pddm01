package com.anushka.roomdemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.roomdemo.recycle.CategoriaRecycleViewAdapter
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ActivityCategoriaBinding
import com.anushka.roomdemo.goToActivity
import com.anushka.roomdemo.model.Categoria
import com.anushka.roomdemo.viewmodel.UsuarioViewModel
import com.anushka.roomdemo.viewmodelfactory.UsuarioViewModelFactory
import com.anushka.roomdemo.model.CompraDatabase
import com.anushka.roomdemo.model.DataShared
import com.anushka.roomdemo.repository.CategoriaRepository
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.sharedPreference
import com.anushka.roomdemo.viewmodel.CategoriaViewModel
import com.anushka.roomdemo.viewmodelfactory.CategoriaViewModelFactory

class CategoriaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriaBinding
    private lateinit var categoriaViewModel: CategoriaViewModel
    private lateinit var adapter: CategoriaRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_categoria
        )

        val valActivity = DataShared(this.intent.extras!!)

        var idUsuario : Int = valActivity.id_usuario

        val dao = CompraDatabase.getInstance(application).categoriaDao
        val repository = CategoriaRepository(dao,idUsuario)
        val factory =
            CategoriaViewModelFactory(
                repository
            )
        categoriaViewModel = ViewModelProvider(this,factory).get(CategoriaViewModel::class.java)
        categoriaViewModel.activity = this
        binding.myViewModel = categoriaViewModel
        binding.lifecycleOwner = this

        initRecyclerView()


        categoriaViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })


    }

   override fun onBackPressed() {

       finishAffinity()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.action_logout){

            AlertDialog.Builder(this).apply{
                setTitle(getString(R.string.Are_you_sure))
                setPositiveButton(getString(R.string.Yes)){ _, _ ->
                    goToActivity<LoginActivity>()
                }
                setNegativeButton(getString(R.string.cancel)){ _, _ ->
                }
            }.create().show()

        }
        return super.onOptionsItemSelected(item)
    }
    private fun initRecyclerView(){
        binding.categoriaRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CategoriaRecycleViewAdapter {
                selectedItem: Categoria ->
            categoriaViewModel.initSeleccion(selectedItem)
        }
        binding.categoriaRecyclerView.adapter = adapter
        categoriaViewModel.categorias.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
}
