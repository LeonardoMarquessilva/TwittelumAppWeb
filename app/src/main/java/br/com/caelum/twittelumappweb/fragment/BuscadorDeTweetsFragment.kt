package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.lista_tweet_fragment.*

class BuscadorDeTweetsFragment: Fragment(){

    private lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.lista_tweet_fragment, container,false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_busca,menu)

        val botaoBusca = menu?.findItem(R.id.barra_busca)

        val search = botaoBusca?.actionView as SearchView

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(texto: String?): Boolean {
                val filtrados = filtraTweetsPelo(texto)

                lista_tweets?.adapter = TweetAdapter(filtrados)

                return false
            }
        })

    }

    private fun filtraTweetsPelo(texto: String?): ArrayList<Tweet> {

        val filtrados =ArrayList<Tweet>()

        if (!texto.isNullOrEmpty()){

            val tweets = viewModel.tweets()
            val tweetsFiltrados = tweets.value!!.filter {tweet -> tweet.mensagem.contains(texto!!,true) }
            filtrados.addAll(tweetsFiltrados)

    }else {
            filtrados.clear()
        }
        return filtrados
}


    }