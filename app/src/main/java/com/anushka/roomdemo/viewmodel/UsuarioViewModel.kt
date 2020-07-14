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
import com.anushka.roomdemo.goToActivity
import com.anushka.roomdemo.model.Usuario
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.view.CategoriaActivity
import com.anushka.roomdemo.view.LoginActivity
import com.anushka.roomdemo.view.MainActivity
import kotlinx.coroutines.launch


class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel(), Observable {

    val usuarios = repository.usuarios
    private var isUpdateOrDelete = false
    private lateinit var usuarioToUpdateOrDelete: Usuario
    lateinit var activity: Activity

    @Bindable
    val bnd = MutableLiveData<Boolean>()

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun registerUsuario(){
        if(inputName.value==null){
            statusMessage.value = Event("Ingresar nombre.")
        }else if(inputUsername.value==null){
            statusMessage.value = Event("Ingresar usuario")
        }else if(inputPassword.value==null){
            statusMessage.value = Event("Ingresar contraseña")
        }else{
            val username = inputUsername.value!!
            val password = inputPassword.value!!
            val name = inputName.value!!
            val usuario = Usuario(0,name,username,password)
            loginCreate(usuario)
        }
    }
    fun loginCreate(usuario: Usuario) = viewModelScope.launch {
        val user = repository.findUsuario(usuario.username)
        if (user == null) {
            val create = repository.insert(usuario)

           if (create > -1) {
                inputName.value=null
                inputUsername.value = null
                inputPassword.value = null
                statusMessage.value = Event("Usuario creado con exito")
                usuario.id = create.toInt()

            } else {
                statusMessage.value = Event("Intente nuevamente.")
            }
        } else {
            statusMessage.value = Event("Error: usuario ya existe")
        }
    }



    fun insert(usuario: Usuario) = viewModelScope.launch {
        val newRowId = repository.insert(usuario)
        if (newRowId > -1) {
            statusMessage.value =
                Event("Usuario Inserted Successfully $newRowId")
            registrar()
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun update(usuario: Usuario) = viewModelScope.launch {
        val noOfRows = repository.update(usuario)
        if (noOfRows > 0) {
            inputName.value = null
            inputUsername.value = null
            inputPassword.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Actualizar"
            clearAllOrDeleteButtonText.value = "Borrar Todo"
            statusMessage.value =
                Event("$noOfRows Usuario actualizado")
        } else {
            statusMessage.value = Event("Ocurrio un error")
        }

    }

    fun registrar(){
        val myIntent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(myIntent)
    }

    fun delete(usuario: Usuario) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(usuario)

        if (noOfRowsDeleted > 0) {
            inputName.value = null
            inputUsername.value = null
            inputPassword.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Agregar"
            clearAllOrDeleteButtonText.value = "Borrar Todo"
            statusMessage.value =
                Event("$noOfRowsDeleted Usuario borrado")
        } else {
            statusMessage.value = Event("Ocurrio un error")
        }

    }

    fun initUpdateAndDelete(usuario: Usuario) {
        inputName.value = usuario.name
        inputUsername.value = usuario.username
        inputPassword.value = usuario.password
        isUpdateOrDelete = true
        usuarioToUpdateOrDelete = usuario
        saveOrUpdateButtonText.value = "Actualizar"
        clearAllOrDeleteButtonText.value = "Borrar"

    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}