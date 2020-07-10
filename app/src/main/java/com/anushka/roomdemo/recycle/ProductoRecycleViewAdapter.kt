package com.anushka.roomdemo.recycle


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anushka.roomdemo.R
import com.anushka.roomdemo.databinding.ListCategoriaBinding
import com.anushka.roomdemo.databinding.ListItemBinding
import com.anushka.roomdemo.databinding.ListProductoBinding
import com.anushka.roomdemo.model.Categoria
import com.anushka.roomdemo.model.Producto

class ProductoRecycleViewAdapter(private val clickListener:(Producto)->Unit)
    : RecyclerView.Adapter<ProductoViewHolder>()
{
    private val productosList = ArrayList<Producto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListProductoBinding =
            DataBindingUtil.inflate(layoutInflater,
                R.layout.list_producto,parent,false)
        return ProductoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        holder.bind(productosList[position],clickListener)
    }

    fun setList(productos: List<Producto>) {
        productosList.clear()
        productosList.addAll(productos)

    }

}

class ProductoViewHolder(val binding: ListProductoBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(producto: Producto, clickListener:(Producto)->Unit){
        binding.nameTextView.text = producto.name
        binding.observacionTextView.text = producto.observacion
        binding.precioTextView.text = producto.precio.toString()
        /*binding.nameTextView.text = categoria.name
        binding.idUsuarioTextView.text = categoria.idUsername.toString()
        binding.idTextView.text = categoria.id.toString()
        binding.listCategoriaLayout.setOnClickListener{
            clickListener(categoria)
        }*/
    }
}