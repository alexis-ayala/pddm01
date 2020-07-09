package com.anushka.roomdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ActivityLoginBinding
import com.anushka.roomdemo.model.CompraDatabase
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.viewmodel.LoginViewModel
import com.anushka.roomdemo.viewmodelfactory.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )
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
        //initRecyclerView()

        loginViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

    }
}
