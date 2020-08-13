package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TypeOfUserSignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_of_user_sign_up)

        val botonChooseArtistSU = findViewById<Button>(R.id.buttonChooseArtistSU)
        val botonChooseBuyerSU = findViewById<Button>(R.id.buttonChooseBuyerSU)

        botonChooseArtistSU.setOnClickListener {
            val intentoChooseArtistSU = Intent(this@TypeOfUserSignUpActivity, ArtistSignUpActivity::class.java)
            startActivity(intentoChooseArtistSU)
        }

        botonChooseBuyerSU.setOnClickListener {
            val intentoChooseBuyerSU = Intent(this@TypeOfUserSignUpActivity, BuyerSignUpActivity::class.java)
            startActivity(intentoChooseBuyerSU)
        }
    }
}
