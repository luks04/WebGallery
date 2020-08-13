package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ean.mundo.Galeria
import kotlinx.android.synthetic.main.activity_artist_sign_up.*
import java.lang.Exception

class ArtistSignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_sign_up)

        val botonArtistSignUp = findViewById<Button>(R.id.buttonArtistSignUp)
        val botonGoBackSUArtist = findViewById<Button>(R.id.buttonGoBackSUArtist)

        val nombreArtistSU = findViewById<EditText>(R.id.nameArtistSU)
        val usuarioArtistSU = findViewById<EditText>(R.id.userArtistSU)
        val documentoArtistSU = findViewById<EditText>(R.id.idArtistSU)
        val passwordArtistSU = findViewById<EditText>(R.id.passwordArtistSU)

        botonArtistSignUp.setOnClickListener {
            try {
                val name = nombreArtistSU.text.toString()
                val user = usuarioArtistSU.text.toString()
                val id = documentoArtistSU.text.toString().toInt()
                val password = passwordArtistSU.text.toString()

                Galeria.addArtist(user, password, id, name)
                Toast.makeText(this,
                    "¡Artista Agregado Exitosamente!",
                    Toast.LENGTH_LONG).show()
                val intentoQuitArtist = Intent(this@ArtistSignUpActivity, MainActivity::class.java)
                startActivity(intentoQuitArtist)
            }
            catch (e: Exception){
                Toast.makeText(this,
                    "${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
        botonGoBackSUArtist.setOnClickListener {
            val intentoGoBackSUArtist = Intent(this@ArtistSignUpActivity, MainActivity::class.java)
            startActivity(intentoGoBackSUArtist)
        }
    }
}
