package com.anushka.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_compra_usuario")
data class Usuario (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "username")
    var username : String,

    @ColumnInfo(name = "password")
    var password : String
)