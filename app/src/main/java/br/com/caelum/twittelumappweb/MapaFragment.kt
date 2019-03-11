package br.com.caelum.twittelumappweb

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : SupportMapFragment(),OnMapReadyCallback {

    private lateinit var tweetViewModel: TweetViewModel

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        tweetViewModel = ViewModelProviders.of(activity!!,ViewModelFactory)
                .get(TweetViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val lista = tweetViewModel.tweets().value
        lista?.forEach{tweet->
            val markerOptions = MarkerOptions()
            markerOptions.position(LatLng(tweet.latitude,tweet.longitude))
            markerOptions.title(tweet.dono.nome)

            googleMap?.addMarker(markerOptions)

        }

    }
}