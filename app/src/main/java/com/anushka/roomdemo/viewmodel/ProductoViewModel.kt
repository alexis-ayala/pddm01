package com.anushka.roomdemo.viewmodel

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.Event
import com.anushka.roomdemo.model.Categoria
import com.anushka.roomdemo.model.Producto
import com.anushka.roomdemo.model.Usuario
import com.anushka.roomdemo.repository.CategoriaRepository
import com.anushka.roomdemo.repository.ProductoRepository
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.view.CategoriaActivity
import com.anushka.roomdemo.view.McategoriaActivity
import kotlinx.coroutines.launch


class ProductoViewModel(private val repository: ProductoRepository) : ViewModel(), Observable {
    lateinit var activity : Activity
    var idUsuario : Int = repository.idUsuario
    var idCategoria : Int = repository.idCategoria

    val productos = repository.productos
    lateinit var categoria : Categoria
    private lateinit var productoSeleccionada: Producto

    @Bindable
    val createProductoButtonText = MutableLiveData<String>()

    @Bindable
    val inputNameCategoria = MutableLiveData<String>()

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputObservacion = MutableLiveData<String>()

    @Bindable
    val inputPrecio = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        iniCategoria()
    }
    fun iniCategoria()= viewModelScope.launch {
        categoria = repository.getCategoria()!!
        createProductoButtonText.value = "Agregar"
        inputNameCategoria.value = categoria.name
    }
    fun initSeleccion(producto: Producto) {
        productoSeleccionada = producto
        statusMessage.value =
            Event("Producto: id = ${producto.id}, nombre = ${producto.name} y usuario = ${producto.idUsername}")
        //val myIntent = Intent(activity, McategoriaActivity::class.java)
        //activity.startActivity(myIntent)
    }

    fun createProducto(){
        if(inputName.value==null){
            statusMessage.value =
                Event("Ingresar descripción")
        }else if(inputPrecio.value==null){
            statusMessage.value =
                Event("Ingresar precio")
        }else if(inputObservacion.value==null){
            statusMessage.value =
                Event("Ingresar observación")
        }else{
            val nombre = inputName.value!!
            val precio : Double = Math.round((inputPrecio.value!!).toDouble()* 100.0) / 100.0
            val observacion = inputObservacion.value!!
            val nomCat = inputNameCategoria.value!!
            var producto = Producto(0,nombre,precio,observacion,idCategoria,nomCat,idUsuario)
            insert(producto)
            inputName.value=""
            inputObservacion.value=""
            inputPrecio.value=""
        }
    }
    fun updateCategoria()= viewModelScope.launch {
        if(inputNameCategoria.value==null){
            statusMessage.value =
                Event("Ingresar nombre categoría")
        }else{
            categoria.name = inputNameCategoria.value!!
            var newUpdate = repository.updateCategoria(categoria!!)
            if (newUpdate > -1) {
                statusMessage.value =
                    Event("Se ha modificado categoría.")
            } else {
                statusMessage.value = Event("Error desconocido")
            }
        }
    }
    fun deleteCate() = viewModelScope.launch {
        val newRowId = repository.deleteCategoria(categoria!!)
        if (newRowId > -1) {
            statusMessage.value =
                Event("Se ha borrado categoría.")
            repository.deleteCatProducto() //borra los productos de la categoría
        } else {
            statusMessage.value = Event("Error desconocido")
        }
        statusMessage.value = Event("Llega deletecat")
    }
    fun insertCate(categoria: Categoria) = viewModelScope.launch {

        val newRowId = repository.updateCategoria(categoria)
        if (newRowId > -1) {
            statusMessage.value =
                Event("Cambio efectuado.")
            val nomCate = inputNameCategoria.value!!
            repository.updateAll(nomCate)
        } else {
            statusMessage.value = Event("Error desconocido")
        }
    }
    fun insert(producto: Producto) = viewModelScope.launch {

        val newRowId = repository.insert(producto)
        if (newRowId > -1) {
            statusMessage.value =
                Event("Producto ingresado.")
        } else {
            statusMessage.value = Event("Error desconocido")
        }
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}