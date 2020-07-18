package com.anushka.roomdemo.model

import android.os.Bundle
import java.lang.Exception

class DataShared (var parametro: Bundle) {
    var id_usuario : Int = 0
    var username : String = ""
    var name_usuario : String = ""
    var id_categoria : Int = 0
    var id_producto : Int = 0

    init {
        if(parametro.getInt("idusuario")!=null){
            id_usuario = parametro.getInt("idusuario")
        }
        if(parametro.getInt("id_categoria")!=null){
            id_categoria = parametro.getInt("id_categoria")
        }
        if(parametro.getInt("id_producto")!=null){
            id_producto = parametro.getInt("id_producto")
        }
        if(parametro.getString("name_usuario")!=null){
            name_usuario = parametro.getString("name_usuario").toString()
        }
        if(parametro.getString("username")!=null){
            username = parametro.getString("username").toString()
        }
    }
}