package com.anushka.roomdemo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anushka.roomdemo.dao.CategoriaDao
import com.anushka.roomdemo.dao.UsuarioDAO

@Database(entities = [Usuario::class,Categoria::class],version = 1)
abstract class CompraDatabase : RoomDatabase() {

    abstract val usuarioDAO : UsuarioDAO
    abstract val categoriaDao : CategoriaDao

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
                                 "db_compra_3"
                          ).build()
                      }
                  return instance
              }
          }

    }
}

