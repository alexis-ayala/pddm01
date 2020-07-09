package com.anushka.roomdemo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.viewmodel.UsuarioViewModel
import com.anushka.roomdemo.repository.UsuarioRepository
import java.lang.IllegalArgumentException

class UsuarioViewModelFactory(private val repository: UsuarioRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UsuarioViewModel::class.java)){
            return UsuarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}