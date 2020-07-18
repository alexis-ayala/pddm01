package com.anushka.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoria")
data class Categoria (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cat_id")
    var cat_id : Int,

    @ColumnInfo(name = "idusuario")
    var idusuario: Int,

    @ColumnInfo(name = "nombre")
    var nombre : String,

    @ColumnInfo(name = "limite")
    var limite : Double
)