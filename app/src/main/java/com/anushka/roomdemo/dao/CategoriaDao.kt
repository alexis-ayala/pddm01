package com.anushka.roomdemo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anushka.roomdemo.model.Categoria

@Dao
interface CategoriaDao {

    @Insert
    suspend fun insertCategoria(categoria: Categoria) : Long

    @Update
    suspend fun updateCategoria(categoria: Categoria) : Int

    @Delete
    suspend fun deleteCategoria(categoria: Categoria) : Int

    @Query("DELETE FROM table_compra_categoria")
    suspend fun deleteAll() : Int

    @Query("DELETE FROM table_compra_categoria where idUsername=:idUsuario")
    suspend fun deleteAllUsuario(idUsuario: Int) : Int

    @Query("SELECT * FROM table_compra_categoria")
    fun getAllCategoria():LiveData<List<Categoria>>

    @Query("SELECT * FROM table_compra_categoria where idUsername=:idUsername")
    fun getAllCategoriaUsuario(idUsername: Int):LiveData<List<Categoria>>

    @Query("SELECT * FROM table_compra_categoria where id=:id")
    suspend fun getCategoria(id: Int): Categoria
}