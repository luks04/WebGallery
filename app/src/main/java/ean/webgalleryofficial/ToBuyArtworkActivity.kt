package ean.webgalleryofficial

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ean.collections.IList
import ean.mundo.Galeria
import ean.mundo.Obra
import kotlin.Exception

class ToBuyArtworkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_buy_artwork)

        val loggedBuyer = Galeria.loginDataBuyer

        val botonCompletePay = findViewById<Button>(R.id.buttonCompletePay)
        val botonCancelPurchase = findViewById<Button>(R.id.buttonCancelPurchase)
        val botonToSeePurchaseReview = findViewById<Button>(R.id.buttonToSeePurchaseReview)
        val identificacionArtworkCompletePay = findViewById<EditText>(R.id.idArtworkCompletePay)
        val documentoArtistCompletePay = findViewById<EditText>(R.id.idArtistCompletePay)
        val verReview = findViewById<TextView>(R.id.seeReview)

        botonToSeePurchaseReview.setOnClickListener {
            try {
                val obra = Galeria.obras[identificacionArtworkCompletePay.text.toString().toInt()]
                val idArtist = documentoArtistCompletePay.text.toString().toInt()
                val idArtwork = identificacionArtworkCompletePay.text.toString().toInt()

                fun isOnList(lista: IList<Obra>, elem: Obra): Boolean{
                    var flag = false
                    for (i in lista){
                        if (elem.IDObra == obra!!.IDObra){
                            flag = true
                            break
                        }
                    }
                    return flag
                }

                val listaArtworks = Galeria.getArtworksByArtist(idArtist)
                val elemArtwork = Galeria.obras[idArtwork]

                if (isOnList(listaArtworks, elemArtwork!!) || listaArtworks.isEmpty){
                    val comisionArtista = obra!!.precio
                    val comisionGaleria = obra.precio * 0.02
                    val total = comisionArtista + comisionArtista
                    val finalReview = "Comision Galeria: $comisionGaleria\nComision Artista: $comisionArtista\n\nTotal: $total"
                    verReview.text = finalReview
                }else{
                    throw Exception ("¡La obra ${Galeria.obras[idArtwork]!!.nombre} no pertenece al artista con ID: $idArtist!")
                }
            }catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        botonCompletePay.setOnClickListener {
            try {
                val idObra = identificacionArtworkCompletePay.text.toString().toInt()
                val idArtist = documentoArtistCompletePay.text.toString().toInt()
                Galeria.toSellArtwork(idArtist, idObra, loggedBuyer!!.getID())
                Toast.makeText(this,
                    "¡Compra Realizada Exitosamente!",
                    Toast.LENGTH_LONG).show()
                val intentoFinalizarCompra = Intent(this@ToBuyArtworkActivity, BuyerProfileActivity::class.java)
                startActivity(intentoFinalizarCompra)
            }catch (e: Exception) {
                Toast.makeText(
                    this,
                    "${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        botonCancelPurchase.setOnClickListener {
            val intentoCancelPurchase = Intent(this@ToBuyArtworkActivity, BuyerProfileActivity::class.java)
            startActivity(intentoCancelPurchase)
        }
    }
}
