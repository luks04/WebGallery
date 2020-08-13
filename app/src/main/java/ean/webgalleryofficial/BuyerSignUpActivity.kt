package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ean.mundo.Galeria
import java.lang.Exception

class BuyerSignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_sign_up)

        val botonBuyerSignUp = findViewById<Button>(R.id.buttonBuyerSignUp)
        val botonGoBackSUBuyer = findViewById<Button>(R.id.buttonGoBackSUBuyer)

        val nombreBuyerSU = findViewById<EditText>(R.id.nameBuyerSU)
        val usuarioBuyerSU = findViewById<EditText>(R.id.userBuyerSU)
        val documentoBuyerSU = findViewById<EditText>(R.id.idBuyerSU)
        val passwordBuyerSU = findViewById<EditText>(R.id.passwordBuyerSU)

        botonBuyerSignUp.setOnClickListener {
            try {
                val name = nombreBuyerSU.text.toString()
                val user = usuarioBuyerSU.text.toString()
                val id = documentoBuyerSU.text.toString().toInt()
                val password = passwordBuyerSU.text.toString()

                Galeria.addBuyer(user, password, id, name)
                Toast.makeText(this,
                    "¡Comprador Agregado Exitosamente!",
                    Toast.LENGTH_LONG).show()
                val intentoQuitBuyer = Intent(this@BuyerSignUpActivity, MainActivity::class.java)
                startActivity(intentoQuitBuyer)
            }
            catch (e: Exception){
                Toast.makeText(this,
                    "${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
        botonGoBackSUBuyer.setOnClickListener {
            val intentoGoBackSUArtist = Intent(this@BuyerSignUpActivity, MainActivity::class.java)
            startActivity(intentoGoBackSUArtist)
        }
    }
}
