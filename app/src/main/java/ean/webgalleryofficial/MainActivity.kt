package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ean.mundo.Galeria

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Galeria.addDefaultMembers()
        Galeria.setArtistLoginData(null)
        Galeria.setBuyerLoginData(null)

        val botonIniciarSesion = findViewById<Button>(R.id.buttonIniciarSesion)
        val botonRegistrarse = findViewById<Button>(R.id.buttonRegistrarse)

        botonIniciarSesion.setOnClickListener {
            val intentoIniciarSesion = Intent(this@MainActivity, TypeOfUserLogInActivity::class.java)
            startActivity(intentoIniciarSesion)
        }

        botonRegistrarse.setOnClickListener {
            val intentoRegistrarse = Intent(this@MainActivity, TypeOfUserSignUpActivity::class.java)
            startActivity(intentoRegistrarse)
        }
    }
}
