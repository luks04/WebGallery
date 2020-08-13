package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TypeOfUserLogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_of_user_log_in)

        val botonChooseArtistLI = findViewById<Button>(R.id.buttonChooseArtistLI)
        val botonChooseBuyerLI = findViewById<Button>(R.id.buttonChooseBuyerLI)

        botonChooseArtistLI.setOnClickListener {
            val intentoChooseArtistLI = Intent(this@TypeOfUserLogInActivity, ArtistLogInActivity::class.java)
            startActivity(intentoChooseArtistLI)
        }

        botonChooseBuyerLI.setOnClickListener {
            val intentoChooseBuyerLI = Intent(this@TypeOfUserLogInActivity, BuyerLogInActivity::class.java)
            startActivity(intentoChooseBuyerLI)
        }
    }
}
