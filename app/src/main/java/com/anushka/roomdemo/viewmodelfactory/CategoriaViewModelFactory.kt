package com.anushka.roomdemo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.repository.CategoriaRepository
import com.anushka.roomdemo.viewmodel.UsuarioViewModel
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.viewmodel.CategoriaViewModel
import java.lang.IllegalArgumentException

class CategoriaViewModelFactory(private val repository: CategoriaRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoriaViewModel::class.java)){
            return CategoriaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}