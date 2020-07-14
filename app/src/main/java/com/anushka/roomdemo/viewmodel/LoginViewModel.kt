package com.anushka.roomdemo.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.Event
//import com.anushka.roomdemo.view.SignUpActivity
import com.anushka.roomdemo.model.Usuario
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.sharedPreference
import com.anushka.roomdemo.view.CategoriaActivity
import com.anushka.roomdemo.view.LoginActivity
import com.anushka.roomdemo.view.MainActivity
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: UsuarioRepository) : ViewModel(), Observable {

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
    val loginButtonText = MutableLiveData<String>()

    @Bindable
    val createLoginButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        loginButtonText.value = "Acceder"
        createLoginButtonText.value = "Registrar"
    }

    fun loginUsuario() {
        if (inputUsername.value == null) {
            statusMessage.value = Event("Favor ingresar usuario")
        } else if (inputPassword.value == null) {
            statusMessage.value = Event("Favor ingresar contraseña")
        } else {
            val username = inputUsername.value!!
            val password = inputPassword.value!!
            loginUser(username,password)
        }
    }

    fun loginUser(username: String, password: String) = viewModelScope.launch {
        val login : Usuario? = repository.getUsuario(username,password)
        if(login!=null){
            inputUsername.value = null
            inputPassword.value = null
            statusMessage.value = Event("Bienvenido.")
            bnd.value = true
            loguear(login)
        }else{
            bnd.value = false
            statusMessage.value = Event("Credenciales incorrectas.")
        }
    }
    fun loguear(usuario: Usuario){
        val myIntent = Intent(activity, CategoriaActivity::class.java)
        myIntent.putExtra("id_usuario",usuario.id)
        myIntent.putExtra("username", usuario.username)
        myIntent.putExtra("name_usuario",usuario.name)
        activity.startActivity(myIntent)
    }

    fun loger(){
        val myIntent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(myIntent)
    }

    fun newUser() {
        val myIntent = Intent(activity, MainActivity::class.java)
        activity.startActivity(myIntent)
    }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}