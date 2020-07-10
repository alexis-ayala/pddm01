package com.anushka.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_compra_producto")
data class Producto (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "precio")
    var precio : Double,

    @ColumnInfo(name = "observacion")
    var observacion : String,

    @ColumnInfo(name = "idCategoria")
    var idCategoria : Int,

    @ColumnInfo(name = "nombreCategoria")
    var nombreCategoria : String,

    @ColumnInfo(name = "idUsername")
    var idUsername : Int

)