package com.anushka.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articulo")
data class Producto (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "articulo_id")
    var articulo_id : Int,

    @ColumnInfo(name = "cat_id")
    var cat_id: Int,

    @ColumnInfo(name = "nombre")
    var nombre: String,

    @ColumnInfo(name = "precio")
    var precio: Double,

    @ColumnInfo(name = "observacion")
    var observacion: String

)