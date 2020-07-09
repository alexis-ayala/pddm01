package com.anushka.roomdemo.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anushka.roomdemo.Event
import com.anushka.roomdemo.model.Usuario
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.view.CategoriaActivity
import com.anushka.roomdemo.view.HomeActivity
import com.anushka.roomdemo.view.MainActivity
import kotlinx.coroutines.launch


class LoginViewModel(private val repository: UsuarioRepository) : ViewModel(), Observable {

    lateinit var activity: Activity

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
            statusMessage.value = Event("Si existe.")
            val myIntent = Intent(activity, CategoriaActivity::class.java)
            activity.startActivity(myIntent)
        }else{
            statusMessage.value = Event("Credenciales incorrectas.")
        }
    }
    fun registerUsuario(){

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}