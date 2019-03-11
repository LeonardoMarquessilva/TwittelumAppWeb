package br.com.caelum.twittelumappweb

import android.arch.lifecycle.MutableLiveData

class UsuarioRepository (private val client:UsuarioWebClient){
    private var usuario:Usuario? = Usuario()
    val estaLogado: MutableLiveData<Boolean> =MutableLiveData()

    fun loga(usuario: Usuario) = client.fazLogin(usuario,sucesso())

    fun cria (usuario: Usuario) = client.cria(usuario, sucesso())

    fun usuarioLogado(): Usuario = usuario!!

    fun desloga(){
        estaLogado.value = false
        this.usuario = Usuario()
    }

    private fun sucesso() = { usuario: Usuario ->
        estaLogado.value = true
        this.usuario=usuario

    }

}