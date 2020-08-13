package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ean.collections.IDictionary
import ean.mundo.Artista
import ean.mundo.Galeria
import java.lang.Exception

class ArtistLogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_log_in)

        val botonArtistLogIn = findViewById<Button>(R.id.buttonArtistLogIn)
        val botonGoBackLIArtist = findViewById<Button>(R.id.buttonGoBackLIArtist)

        val documentoArtistLogIn = findViewById<EditText>(R.id.idArtistLI)
        val passwordArtistLogIn = findViewById<EditText>(R.id.passwordArtistLI)


        botonArtistLogIn.setOnClickListener {
            try {
                val id = documentoArtistLogIn.text.toString().toInt()
                val password = passwordArtistLogIn.text.toString()

                if (Galeria.getAllArtists().containsKey(id) || Galeria.getAllArtists().isEmpty){
                    val artista = Galeria.artistas[id]
                    if (artista!!.getID() == id && artista.getPassword() == password){
                        Galeria.setArtistLoginData(artista)
                        Toast.makeText(this,
                            "¡Bienvenido ${artista.getUser()}!",
                            Toast.LENGTH_LONG).show()
                        val intentoArtistLogIn = Intent(this@ArtistLogInActivity, ArtistProfileActivity::class.java)
                        startActivity(intentoArtistLogIn)
                    }else{
                        throw Exception("¡La contraseña y/o el documento son incorrectos!")
                    }
                }else{
                    throw Exception("¡El artista con ID: $id NO existe!")
                }
            }
            catch (e: Exception){
                Toast.makeText(this,
                    "${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
        botonGoBackLIArtist.setOnClickListener {
            val intentoGoBackSUArtist = Intent(this@ArtistLogInActivity, MainActivity::class.java)
            startActivity(intentoGoBackSUArtist)
        }
    }
}
