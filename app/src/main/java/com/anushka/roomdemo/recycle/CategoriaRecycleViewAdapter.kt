package com.anushka.roomdemo.recycle


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ListCategoriaBinding
import com.anushka.roomdemo.model.Categoria

class CategoriaRecycleViewAdapter(private val clickListener:(Categoria)->Unit)
    : RecyclerView.Adapter<CategoriaViewHolder>()
{
    private val categoriasList = ArrayList<Categoria>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListCategoriaBinding =
            DataBindingUtil.inflate(layoutInflater,
                R.layout.list_categoria,parent,false)
        return CategoriaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoriasList.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.bind(categoriasList[position],clickListener)
    }

    fun setList(categorias: List<Categoria>) {
        categoriasList.clear()
        categoriasList.addAll(categorias)

    }

}

class CategoriaViewHolder(val binding: ListCategoriaBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(categoria: Categoria, clickListener:(Categoria)->Unit){
        binding.nameTextView.text = categoria.nombre
        binding.idUsuarioTextView.text = categoria.idusuario.toString()
        binding.idTextView.text = categoria.cat_id.toString()
        binding.listCategoriaLayout.setOnClickListener{
            clickListener(categoria)
        }
    }
}