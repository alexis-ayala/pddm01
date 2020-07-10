package com.anushka.roomdemo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.repository.CategoriaRepository
import com.anushka.roomdemo.repository.ProductoRepository
import com.anushka.roomdemo.viewmodel.UsuarioViewModel
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.viewmodel.CategoriaViewModel
import com.anushka.roomdemo.viewmodel.ProductoViewModel
import java.lang.IllegalArgumentException

class ProductoViewModelFactory(private val repository: ProductoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductoViewModel::class.java)){
            return ProductoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}