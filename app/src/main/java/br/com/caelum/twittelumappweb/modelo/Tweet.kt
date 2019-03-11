package br.com.caelum.twittelumappweb.modelo

import br.com.caelum.twittelumappweb.Usuario

data class Tweet(val mensagem: String,
                 val foto: String?,
                 val dono: Usuario = Usuario(),
                 val latitude: Double = 0.0,
                 val longitude: Double = 0.0,
                 val id: Long = 0) {

    override fun toString(): String {
        return mensagem
    }

}