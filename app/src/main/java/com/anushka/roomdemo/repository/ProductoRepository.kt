package com.anushka.roomdemo.repository

import com.anushka.roomdemo.dao.CategoriaDao
import com.anushka.roomdemo.dao.ProductoDao
import com.anushka.roomdemo.model.Categoria
import com.anushka.roomdemo.model.Producto
import java.lang.reflect.Type

class ProductoRepository(private val dao : ProductoDao, private val daoCategoria : CategoriaDao, val idUsuario: Int,val idCategoria: Int) {
    val productos = dao.getAllProductoUsuario(idUsuario,idCategoria)

    suspend fun insert(producto: Producto):Long{
        return dao.insert(producto)
    }

    suspend fun update(producto: Producto):Int{
        return dao.update(producto)
    }
    suspend fun updateAll(cat: String):Int{
        return dao.updateAll(idCategoria,cat)
    }

    suspend fun delete(producto: Producto) : Int{
        return dao.delete(producto)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAllUsuario(idUsuario)
    }
    suspend fun deleteCategoria(categoria: Categoria) : Int{
        return daoCategoria.deleteCategoria(categoria)
    }

    suspend fun getProducto(id: Int): Producto? {
        return dao.getProducto(id)
    }
    suspend fun updateCategoria(categoria: Categoria):Int{
        return daoCategoria.updateCategoria(categoria)
    }
    suspend fun getCategoria(): Categoria?{
        return daoCategoria.getCategoria(idCategoria)
    }
}