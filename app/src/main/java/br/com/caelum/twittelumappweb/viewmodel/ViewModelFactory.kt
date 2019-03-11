package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.TweetWebClient
import br.com.caelum.twittelumappweb.UsuarioRepository
import br.com.caelum.twittelumappweb.UsuarioWebClient
import br.com.caelum.twittelumappweb.data.TweetRepository

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private val tweetRepository = TweetRepository(TweetWebClient(InicializadorDoRetrofit.retrofit))

    private val usuarioRepository = UsuarioRepository(UsuarioWebClient(InicializadorDoRetrofit.retrofit))

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.name == TweetViewModel::class.java.name){
            modelClass.getConstructor(TweetRepository::class.java).newInstance(tweetRepository)
        }else {
            modelClass.getConstructor(UsuarioRepository::class.java).newInstance(usuarioRepository)
        }
    }


}