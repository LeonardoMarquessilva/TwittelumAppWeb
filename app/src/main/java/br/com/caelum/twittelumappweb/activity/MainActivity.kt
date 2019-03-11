package br.com.caelum.twittelumappweb.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import br.com.caelum.twittelumappweb.LoginActivity
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.UsuarioViewModel
import br.com.caelum.twittelumappweb.fragment.BuscadorDeTweetsFragment
import br.com.caelum.twittelumappweb.fragment.ListaTweetsFragment
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var teetViewModel: TweetViewModel
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teetViewModel = ViewModelProviders.of(this,ViewModelFactory)
                .get(TweetViewModel::class.java)

        usuarioViewModel = ViewModelProviders.of(this,ViewModelFactory)
                .get(UsuarioViewModel::class.java)

        listenerBottomNavigation()

        fabMain.setOnClickListener { startActivity(Intent(this, TweetActivity::class.java)) }
    }

    private fun listenerBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.menu_tweets ->{
                    exibe(ListaTweetsFragment())
                    true
                }

                R.id.menu_busca -> {
                    exibe(BuscadorDeTweetsFragment())
                    true
                }

                R.id.menu_mapa -> {
                    exibe(SupportMapFragment())
                    true
                }

                else ->{
                    false
                }
            }
        }

        bottom_navigation.selectedItemId = R.id.menu_busca
    }

    private fun exibe(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frame_principal, fragment)

        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sair,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.menu_sair){
            usuarioViewModel.desloga()
            voltaProLogin()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun voltaProLogin() {
        finish()
        startActivity(Intent(this,LoginActivity::class.java))
    }

}
