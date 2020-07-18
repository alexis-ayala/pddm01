package com.anushka.roomdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class Usuario (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idusuario")
    var id_usuario : Int,

    @ColumnInfo(name = "nomusuario")
    var name : String,

    @ColumnInfo(name = "contra")
    var contra : String
)