package com.anushka.roomdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anushka.roomdemo.recycle.MyRecyclerViewAdapter
import com.anushka.roomdemo.R
import com.anushka.roomdemo.viewmodel.UsuarioViewModel
import com.anushka.roomdemo.viewmodelfactory.UsuarioViewModelFactory
import com.anushka.roomdemo.databinding.ActivityMainBinding
import com.anushka.roomdemo.goToActivity
import com.anushka.roomdemo.model.CompraDatabase
import com.anushka.roomdemo.repository.UsuarioRepository
import com.anushka.roomdemo.sharedPreference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usuarioViewModel: UsuarioViewModel
    private lateinit var adapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        val dao = CompraDatabase.getInstance(application).usuarioDAO
        val repository = UsuarioRepository(dao)
        val factory =
            UsuarioViewModelFactory(
                repository
            )
        usuarioViewModel = ViewModelProvider(this,factory).get(UsuarioViewModel::class.java)
        binding.myViewModel = usuarioViewModel
        binding.lifecycleOwner = this
        //initRecyclerView()

        usuarioViewModel.message.observe(this, Observer {
         it.getContentIfNotHandled()?.let {
             Toast.makeText(this, it, Toast.LENGTH_LONG).show()
         }
        })
        buttonGoLogIn.setOnClickListener{
            goToActivity<HomeActivity>()
        }

    }
   /*private fun initRecyclerView(){
       binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
       adapter =
           MyRecyclerViewAdapter({ selectedItem: Usuario ->
               listItemClicked(selectedItem)
           })
       binding.subscriberRecyclerView.adapter = adapter
       displaySubscribersList()
   }*/

    /*private fun displaySubscribersList(){
        usuarioViewModel.usuarios.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(usuario: Usuario){
        //Toast.makeText(this,"selected name is ${usuario.name}",Toast.LENGTH_LONG).show()
        usuarioViewModel.initUpdateAndDelete(usuario)
    }*/
}
