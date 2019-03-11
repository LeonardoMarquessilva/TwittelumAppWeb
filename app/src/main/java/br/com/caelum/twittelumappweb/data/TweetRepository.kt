package br.com.caelum.twittelumappweb.data

import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.TweetWebClient
import br.com.caelum.twittelumappweb.modelo.Tweet

class TweetRepository (private val webClient: TweetWebClient){

    fun salva(tweet: Tweet) = webClient.salva(tweet)

    private val lista = MutableLiveData<List<Tweet>>()

    private fun sucesso()= { list: List<Tweet>->
        lista.value = list

    }

    fun lista() = lista

    fun buscaTweets() = webClient.buscaTweets(sucessoParaLista())

    private fun erro() = { erro: Throwable -> }



    private fun sucessoParaLista() = { tweets: List<Tweet> ->
        lista.postValue(tweets)
    }
}