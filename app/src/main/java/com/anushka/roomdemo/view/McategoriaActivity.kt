package com.anushka.roomdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anushka.roomdemo.recycle.CategoriaRecycleViewAdapter
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ActivityCategoriaBinding
import com.anushka.roomdemo.databinding.ActivityMcategoriaBinding
import com.anushka.roomdemo.model.Categoria
import com.anushka.roomdemo.viewmodel.UsuarioViewModel
import com.anushka.roomdemo.viewmodelfactory.UsuarioViewModelFactory
import com.anushka.roomdemo.model.CompraDatabase
import com.anushka.roomdemo.model.DataShared
import com.anushka.roomdemo.model.Producto
import com.anushka.roomdemo.recycle.ProductoRecycleViewAdapter
import com.anushka.roomdemo.repository.CategoriaRepository
import com.anushka.roomdemo.repository.ProductoRepository
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.viewmodel.CategoriaViewModel
import com.anushka.roomdemo.viewmodel.ProductoViewModel
import com.anushka.roomdemo.viewmodelfactory.CategoriaViewModelFactory
import com.anushka.roomdemo.viewmodelfactory.ProductoViewModelFactory

class McategoriaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMcategoriaBinding
    private lateinit var productoViewModel: ProductoViewModel
    private lateinit var adapter: ProductoRecycleViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_mcategoria
        )

        val valActivity = DataShared(this.intent.extras!!)

        val idUsuario : Int = valActivity.id_usuario
        val idCategoria : Int = valActivity.id_categoria

        val daoProducto = CompraDatabase.getInstance(application).productoDao
        val daoCategoria = CompraDatabase.getInstance(application).categoriaDao
        val repository = ProductoRepository(daoProducto,daoCategoria,idUsuario,idCategoria)
        val factory =
            ProductoViewModelFactory(
                repository
            )
        productoViewModel = ViewModelProvider(this,factory).get(ProductoViewModel::class.java)
        productoViewModel.activity = this
        binding.myViewModel = productoViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

        productoViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun initRecyclerView(){
        binding.productoRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductoRecycleViewAdapter {
                selectedItem: Producto ->
            productoViewModel.initSeleccion(selectedItem)
        }
        binding.productoRecyclerView.adapter = adapter
        productoViewModel.productos.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
}
