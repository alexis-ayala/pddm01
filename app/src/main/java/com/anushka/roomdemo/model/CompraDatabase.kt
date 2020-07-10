package com.anushka.roomdemo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anushka.roomdemo.dao.CategoriaDao
import com.anushka.roomdemo.dao.ProductoDao
import com.anushka.roomdemo.dao.UsuarioDAO
import com.anushka.roomdemo.repository.UsuarioRepository

@Database(entities = [Usuario::class,Categoria::class,Producto::class],version = 1)
abstract class CompraDatabase : RoomDatabase() {

    abstract val usuarioDAO : UsuarioDAO
    abstract val categoriaDao : CategoriaDao
    abstract val productoDao: ProductoDao

    companion object{
      @Volatile
      private var INSTANCE : CompraDatabase? = null
          fun getInstance(context: Context):CompraDatabase{
              synchronized(this){
                  var instance = INSTANCE
                      if(instance==null){
                          instance = Room.databaseBuilder(
                                 context.applicationContext,
                                 CompraDatabase::class.java,
                                 "db_compra_4"
                          ).build()
                      }
                  return instance
              }
          }

    }
}

