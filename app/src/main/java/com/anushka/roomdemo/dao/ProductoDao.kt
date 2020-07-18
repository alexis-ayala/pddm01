package com.anushka.roomdemo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anushka.roomdemo.model.Categoria
import com.anushka.roomdemo.model.Producto

@Dao
interface ProductoDao {

    @Insert
    suspend fun insert(producto: Producto) : Long

    @Update
    suspend fun update(producto: Producto) : Int

    @Delete
    suspend fun delete(producto: Producto) : Int

    @Query("DELETE FROM articulo")
    suspend fun deleteAll() : Int

    @Query("UPDATE categoria set =:nomCat where cat_id=:idCategoria")
    suspend fun updateAll(idCategoria: Int, nomCat: String) : Int

    @Query("DELETE FROM usuario where idusuario=:idUsuario")
    suspend fun deleteAllUsuario(idUsuario: Int) : Int

    @Query("DELETE FROM categoria where cat_id=:idCategoria")
    suspend fun deleteCatProducto(idCategoria: Int) : Int

    @Query("SELECT * FROM articulo")
    fun getAllCategoria():LiveData<List<Producto>>

    @Query("SELECT * FROM articulo where articulo_id=:idUsername and cat_id=:idCategoria")
    fun getAllProductoUsuario(idUsername: Int, idCategoria: Int):LiveData<List<Producto>>

    @Query("SELECT * FROM articulo where articulo_id=:id")
    suspend fun getProducto(id: Int): Producto
}