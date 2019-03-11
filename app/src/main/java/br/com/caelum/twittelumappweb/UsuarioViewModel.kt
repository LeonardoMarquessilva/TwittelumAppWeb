package br.com.caelum.twittelumappweb

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.com.caelum.twittelumappweb.data.TweetRepository

class UsuarioViewModel (private val repository: UsuarioRepository) : ViewModel() {

    val temUsuario: MutableLiveData<Boolean> = repository.estaLogado

    val usuarioLogado: Usuario = repository.usuarioLogado()

    fun loga(usuario: Usuario) = repository.loga(usuario)

    fun cria(usuario: Usuario) = repository.cria(usuario)

    fun desloga() = repository.desloga()

}