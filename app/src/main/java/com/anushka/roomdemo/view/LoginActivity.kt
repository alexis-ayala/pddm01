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
import com.anushka.roomdemo.sharedPreference
import com.anushka.roomdemo.viewmodel.LoginViewModel
import com.anushka.roomdemo.viewmodelfactory.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var email:String
    private lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_login
        )

        val dao = CompraDatabase.getInstance(application).usuarioDAO
        val repository = UsuarioRepository(dao)
        val sharedPreference =sharedPreference(application)
        val factory =
            LoginViewModelFactory(
                repository
            )
        sharedPreference.clearSharedPreference()
        loginViewModel = ViewModelProvider(this,factory).get(LoginViewModel::class.java)
        binding.myViewModel = loginViewModel
        binding.lifecycleOwner = this
        loginViewModel.activity = this
        //initRecyclerView()
        loginViewModel.bnd.observe(this, Observer{
            if(it){
                sharedPreference.save("email",email)
                sharedPreference.save("pass",password)
            }else{
                sharedPreference.clearSharedPreference()
            }
        })

        loginViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        buttonLogIn.setOnClickListener {
            email=username_text.editableText.toString()
            password=password_text.editableText.toString()
            loginViewModel.loginUsuario()
        }

    }

    }

