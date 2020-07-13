package com.anushka.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_compra_categoria")
data class Categoria (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "limit")
    var limit : Double,

    @ColumnInfo(name = "idUsername")
    var idUsername : Int
)