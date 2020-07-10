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

    @Query("DELETE FROM table_compra_producto")
    suspend fun deleteAll() : Int

    @Query("UPDATE table_compra_producto set nombreCategoria=:nomCat where idCategoria=:idCategoria")
    suspend fun updateAll(idCategoria: Int, nomCat: String) : Int

    @Query("DELETE FROM table_compra_producto where idUsername=:idUsuario")
    suspend fun deleteAllUsuario(idUsuario: Int) : Int

    @Query("DELETE FROM table_compra_producto where idCategoria=:idCategoria")
    suspend fun deleteCatProducto(idCategoria: Int) : Int

    @Query("SELECT * FROM table_compra_producto")
    fun getAllCategoria():LiveData<List<Producto>>

    @Query("SELECT * FROM table_compra_producto where idUsername=:idUsername and idCategoria=:idCategoria")
    fun getAllProductoUsuario(idUsername: Int, idCategoria: Int):LiveData<List<Producto>>

    @Query("SELECT * FROM table_compra_producto where id=:id")
    suspend fun getProducto(id: Int): Producto
}