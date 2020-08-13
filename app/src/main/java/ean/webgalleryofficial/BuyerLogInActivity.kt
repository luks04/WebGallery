package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ean.mundo.Galeria
import java.lang.Exception

class BuyerLogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer_log_in)

        val botonBuyerLogIn = findViewById<Button>(R.id.buttonBuyerLogIn)
        val botonGoBackLIBuyer = findViewById<Button>(R.id.buttonGoBackLIBuyer)

        val documentoBuyerLogIn = findViewById<EditText>(R.id.idBuyerLI)
        val passwordBuyerLogIn = findViewById<EditText>(R.id.passwordBuyerLI)

        botonBuyerLogIn.setOnClickListener {
            try {
                val id = documentoBuyerLogIn.text.toString().toInt()
                val password = passwordBuyerLogIn.text.toString()

                if (Galeria.getAllBuyers().containsKey(id) || Galeria.getAllBuyers().isEmpty){
                    val comprador = Galeria.compradores[id]
                    if (comprador!!.getID() == id && comprador.getPassword() == password){
                        Galeria.setBuyerLoginData(comprador)
                        Toast.makeText(this,
                            "¡Bienvenido ${comprador.getUser()}!",
                            Toast.LENGTH_LONG).show()
                        val intentoBuyerLogIn = Intent(this@BuyerLogInActivity, BuyerProfileActivity::class.java)
                        startActivity(intentoBuyerLogIn)
                    }else{
                        throw Exception("¡La contraseña y/o el documento son incorrectos!")
                    }
                }else{
                    throw Exception("¡El comprador con ID: $id NO existe!")
                }
            }catch (e: Exception){
                Toast.makeText(this,
                    "${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
        botonGoBackLIBuyer.setOnClickListener {
            val intentoGoBackSUArtist = Intent(this@BuyerLogInActivity, MainActivity::class.java)
            startActivity(intentoGoBackSUArtist)
        }
    }
}
