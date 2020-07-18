package com.anushka.roomdemo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anushka.roomdemo.model.Usuario

@Dao
interface UsuarioDAO {

    @Insert
    suspend fun insertUsuario(usuario: Usuario) : Long

    @Update
    suspend fun updateUsuario(usuario: Usuario) : Int

    @Delete
    suspend fun deleteUsuario(usuario: Usuario) : Int

    @Query("DELETE FROM usuario")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM usuario")
    fun getAllUsuario():LiveData<List<Usuario>>

    @Query("SELECT * FROM usuario where nomusuario=:username and contra=:password")
    suspend fun getUsuario(username: String, password: String): Usuario

    @Query("SELECT * FROM usuario where nomusuario=:username")
    suspend fun findUsuario(username: String): Usuario
}