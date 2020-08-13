package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ean.mundo.Galeria
import java.lang.Exception

class ArtistProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_profile)

        val loggedArtist = Galeria.loginDataArtist

        val comisionArtista = findViewById<TextView>(R.id.artistCommission)
        val aux1 = "$ " + Galeria.getArtistCommission(loggedArtist!!.getID()).toString()
        comisionArtista.text = aux1

        val comisionGaleria = findViewById<TextView>(R.id.galleryCommission)
        val aux2 = "$ " + Galeria.getGalleryCommission()
        comisionGaleria.text = aux2

        val botonAddArtwork = findViewById<Button>(R.id.buttonAddArtwork)
        val botonLogOutArtist = findViewById<Button>(R.id.buttonLogOutArtist)

        botonAddArtwork.setOnClickListener {
            val intentoAddArtwork = Intent(this@ArtistProfileActivity, AddArtworkActivity::class.java)
            startActivity(intentoAddArtwork)
        }
        botonLogOutArtist.setOnClickListener {
            Toast.makeText(this,
                "¡Vuelva pronto ${loggedArtist.getUser()}!",
                Toast.LENGTH_LONG).show()
            val intentoLogOutArtist = Intent(this@ArtistProfileActivity, MainActivity::class.java)
            startActivity(intentoLogOutArtist)
        }
    }
}
