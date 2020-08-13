package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ean.mundo.Galeria
import java.lang.Exception

class AddArtworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_artwork)

        /**
         * fun addArtworkToArtist(idArtist: Int, altura: Double, anchura: Double, profundidad: Double, estilo: String, precio: Double, nombre: String, IDObra: Int){
        //TODO("Agregar una obra a un artista: este método recibe los datos de una obra y la identificación de un artista, y crea la obra y la agrega a la lista de obras del artista. La obra debe tener un estado que indique que está a la venta. Manejar excepción si el artista no existe o la obra está repetida.\n")

        val obra = Obra(nombre, altura, anchura, profundidad, estilo, precio, IDObra, true)

        if (!this.artistas.containsKey(idArtist)){
        throw Exception("El artista con ID: $idArtist NO existe")
        }else{
        for ((a,b) in this.obras) {
        if (obra.compareTo(b) == 0 || this.obras.containsKey(IDObra)) {
        throw Exception("¡La obra ${obra.nombre} está repetida!")
        }
        }
        val artist = this.artistas[idArtist]
        artist!!.obras.add(obra)
        }
        }
         */

        val botonToPublish = findViewById<Button>(R.id.buttonToPublish)
        val botonGoBackAddArtwork = findViewById<Button>(R.id.buttonGoBackAddArtwork)

        val alturaObra = findViewById<EditText>(R.id.heightArtworkAdd)
        val anchuraObra = findViewById<EditText>(R.id.widthArtworkAdd)
        val profundidadObra = findViewById<EditText>(R.id.depthArtworkAdd)

        val nombreObra = findViewById<EditText>(R.id.nameArtWorkAdd)
        val estiloObra = findViewById<EditText>(R.id.styleArtworkAdd)

        val idObra = findViewById<EditText>(R.id.idArtworkAdd)
        val precioObra = findViewById<EditText>(R.id.priceArtworkAdd)


        botonToPublish.setOnClickListener {
            try {
                val loggedArtist = Galeria.loginDataArtist

                val altura = alturaObra.text.toString().toDouble()
                val anchura = anchuraObra.text.toString().toDouble()
                val profundidad = profundidadObra.text.toString().toDouble()

                val nombre = nombreObra.text.toString()
                val estilo= estiloObra.text.toString()

                val id = idObra.text.toString().toInt()
                val precio = precioObra.text.toString().toDouble()

                Galeria.addArtworkToArtist(loggedArtist!!.getID(), altura, anchura, profundidad, estilo, precio, nombre, id)
                Toast.makeText(this,
                    "Obra Publicada Exitosamente",
                    Toast.LENGTH_LONG).show()
                val intentoGoBackArtistProfile = Intent(this@AddArtworkActivity, ArtistProfileActivity::class.java)
                startActivity(intentoGoBackArtistProfile)
            }
            catch (e: Exception){
                Toast.makeText(this,
                    "${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
        botonGoBackAddArtwork.setOnClickListener {
            val intentoGoBackAddArtwork = Intent(this@AddArtworkActivity, ArtistProfileActivity::class.java)
            startActivity(intentoGoBackAddArtwork)
        }
    }
}
