package com.anushka.roomdemo.recycle


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ListItemBinding
import com.anushka.roomdemo.model.Usuario
import kotlinx.android.synthetic.main.list_categoria.view.*

class MyRecyclerViewAdapter(private val clickListener:(Usuario)->Unit)
    : RecyclerView.Adapter<MyViewHolder>()
{
    private val usuariosList = ArrayList<Usuario>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding : ListItemBinding =
          DataBindingUtil.inflate(layoutInflater,
              R.layout.list_item,parent,false)
      return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return usuariosList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(usuariosList[position],clickListener)
    }

    fun setList(usuarios: List<Usuario>) {
        usuariosList.clear()
        usuariosList.addAll(usuarios)

    }

}

class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(usuario: Usuario, clickListener:(Usuario)->Unit){
          binding.nameTextView.text = usuario.name
          binding.usernameTextView.text = usuario.username
          binding.listItemLayout.setOnClickListener{
             clickListener(usuario)
          }
    }
}