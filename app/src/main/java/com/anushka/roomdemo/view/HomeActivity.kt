package com.anushka.roomdemo.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ActivityHomeBinding
import com.anushka.roomdemo.model.CompraDatabase
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.sharedPreference
import com.anushka.roomdemo.viewmodel.LoginViewModel
import com.anushka.roomdemo.viewmodelfactory.LoginViewModelFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
            val dao = CompraDatabase.getInstance(application).usuarioDAO
            val repository = UsuarioRepository(dao)
            val factory =
                LoginViewModelFactory(
                    repository
                )
        loginViewModel = ViewModelProvider(this,factory).get(LoginViewModel::class.java)
        binding.myViewModel = loginViewModel
        binding.lifecycleOwner = this
        loginViewModel.activity = this
        val sharedPreference = sharedPreference(application)

        if (sharedPreference.getValueString("email")!=null && sharedPreference.getValueString("pass")!=null) {
            loginViewModel.loginUser(sharedPreference.getValueString("email")!!,sharedPreference.getValueString("pass")!!)
            }
            else{
            loginViewModel.loger()
            }

    }
}
