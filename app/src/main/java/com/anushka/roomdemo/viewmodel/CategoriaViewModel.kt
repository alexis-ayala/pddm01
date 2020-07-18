package com.anushka.roomdemo.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.Event
import com.anushka.roomdemo.model.Categoria
import com.anushka.roomdemo.model.Usuario
import com.anushka.roomdemo.repository.CategoriaRepository
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.view.CategoriaActivity
import com.anushka.roomdemo.view.McategoriaActivity
import kotlinx.coroutines.launch


class CategoriaViewModel(private val repository: CategoriaRepository) : ViewModel(), Observable {
    lateinit var activity : Activity

    val categorias = repository.categorias
    private lateinit var categoriaSeleccionada: Categoria

    @Bindable
    val createCategoriaButtonText = MutableLiveData<String>()

    @Bindable
    val inputName = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        createCategoriaButtonText.value = "Agregar"
    }

    fun initSeleccion(categoria: Categoria) {
        categoriaSeleccionada = categoria
        statusMessage.value =
            Event("Categoria: id = ${categoria.cat_id}, nombre = ${categoria.nombre} y usuario = ${categoria.idusuario}")
        val myIntent = Intent(activity, McategoriaActivity::class.java)
        myIntent.putExtra("id_usuario",repository.idUsuario)
        myIntent.putExtra("id_categoria",categoria.cat_id)
        activity.startActivity(myIntent)
    }

    fun createCategoria(){
        if(inputName.value!=null){
            val nombre = inputName.value!!
            insert(nombre)
        }else{
            statusMessage.value =
                Event("Ingresar categoria")
        }
    }
    fun insert(cat: String) = viewModelScope.launch {
        val cate : Categoria = Categoria(0,0,"",0.0)

        val newRowId = repository.insert(cate)
        if (newRowId > -1) {
            statusMessage.value =
                Event("Categoría ingresada.")
            inputName.value=""
        } else {
            statusMessage.value = Event("Error desconocido")
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}