package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ean.mundo.Galeria
import kotlin.Exception

class BuyerProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_profile)

        val loggedBuyer = Galeria.loginDataBuyer

        val listaArtistArtWorks = findViewById<TextView>(R.id.listOfArtistArtworks)
        val botonSearchArtistArtworks = findViewById<Button>(R.id.buttonSearchArtistArtworks)
        val documentoIDArtist = findViewById<EditText>(R.id.idSearchArtist)
        val botonToSeePurchasedArtworks = findViewById<TextView>(R.id.buttonToSeePurchasedArtworks)
        val botonLogOutBuyer = findViewById<Button>(R.id.buttonLogOutBuyer)
        val botonBuyArtwork = findViewById<Button>(R.id.buttonBuyArtwork)

        botonSearchArtistArtworks.setOnClickListener {
            try {
                val idArtist = documentoIDArtist.text.toString().toInt()
                if (Galeria.getArtworksByArtist(idArtist).isEmpty){
                    throw Exception ("¡El artista no ha publicado ninguna obra hasta el momento!")
                }else{
                    var stringObras = ""
                    for (i in Galeria.getArtworksByArtist(idArtist)){
                        stringObras += i.toString()
                    }
                    listaArtistArtWorks.text = stringObras
                }
            }catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        botonToSeePurchasedArtworks.setOnClickListener {
            try {
                if (Galeria.getPurchasedArtworks(loggedBuyer!!.getID()).isEmpty){
                    throw Exception ("¡No has adquirido ninguna obra hasta el momento!")
                }else{
                    val intentoToSeePurchasedArworks = Intent(this@BuyerProfileActivity, SeePurchasedArtworksActivity::class.java)
                    startActivity(intentoToSeePurchasedArworks)
                }
            }catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        botonLogOutBuyer.setOnClickListener {
            Toast.makeText(this,
                "¡Vuelva pronto ${loggedBuyer!!.getUser()}!",
                Toast.LENGTH_LONG).show()
            val intentoLogOutBuyer = Intent(this@BuyerProfileActivity, MainActivity::class.java)
            startActivity(intentoLogOutBuyer)
        }
        botonBuyArtwork.setOnClickListener {
            val intentoBuyArtwork = Intent(this@BuyerProfileActivity, ToBuyArtworkActivity::class.java)
            startActivity(intentoBuyArtwork)
        }
    }
}
