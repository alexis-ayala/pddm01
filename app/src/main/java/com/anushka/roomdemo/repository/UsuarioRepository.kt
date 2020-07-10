package com.anushka.roomdemo.repository

import com.anushka.roomdemo.dao.UsuarioDAO
import com.anushka.roomdemo.model.Usuario

class UsuarioRepository(private val dao : UsuarioDAO) {

    val usuarios = dao.getAllUsuario()

    suspend fun insert(usuario: Usuario):Long{
       return dao.insertUsuario(usuario)
    }

    suspend fun update(usuario: Usuario):Int{
        return dao.updateUsuario(usuario)
    }

    suspend fun delete(usuario: Usuario) : Int{
       return dao.deleteUsuario(usuario)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }

    suspend fun getUsuario(username: String, password: String): Usuario? {
        return dao.getUsuario(username,password)
    }
    suspend fun findUsuario(username: String): Usuario? {
        return dao.findUsuario(username)
    }
}