package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ean.mundo.Galeria

class SeePurchasedArtworksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_purchased_artworks)

        val loggedBuyer = Galeria.loginDataBuyer

        val listaArtWorks = findViewById<TextView>(R.id.listOfArtworks)
        val botonGoBackSeeBuyerArworks = findViewById<Button>(R.id.buttonGoBackSeeBuyerArworks)

        botonGoBackSeeBuyerArworks.setOnClickListener {
            val intentoGoBackSeeBuyerArworks = Intent(this@SeePurchasedArtworksActivity, BuyerProfileActivity::class.java)
            startActivity(intentoGoBackSeeBuyerArworks)
        }

        var stringObras = ""
        for (i in Galeria.getPurchasedArtworks(loggedBuyer!!.getID())){
            stringObras += i.toString()
        }

        listaArtWorks.text = stringObras
    }
}
