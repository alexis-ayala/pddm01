package com.anushka.roomdemo.repository

import com.anushka.roomdemo.dao.CategoriaDao
import com.anushka.roomdemo.model.Categoria

class CategoriaRepository(private val dao : CategoriaDao) {

    val categorias = dao.getAllCategoriaUsuario(1)

    suspend fun insert(categoria: Categoria):Long{
        return dao.insertCategoria(categoria)
    }

    suspend fun update(categoria: Categoria):Int{
        return dao.updateCategoria(categoria)
    }

    suspend fun delete(categoria: Categoria) : Int{
        return dao.deleteCategoria(categoria)
    }

    suspend fun deleteAll(idUsuario: Int) : Int{
        return dao.deleteAllUsuario(idUsuario)
    }

    suspend fun getCategoria(id: Int): Categoria? {
        return dao.getCategoria(id)
    }

}