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

    @Query("DELETE FROM table_compra_usuario")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM table_compra_usuario")
    fun getAllUsuario():LiveData<List<Usuario>>

    @Query("SELECT * FROM table_compra_usuario where username=:username and password=:password")
    suspend fun getUsuario(username: String, password: String): Usuario
}