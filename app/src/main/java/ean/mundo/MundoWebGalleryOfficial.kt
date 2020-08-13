package ean.mundo

import ean.collections.*

/**
 * Clase Obra
 */
data class Obra(var nombre: String,
                var altura: Double,
                var anchura: Double,
                var profundidad: Double,
                var estilo: String,
                var precio: Double,
                val IDObra: Int,
                var isOnSale: Boolean): Comparable<Obra>{

    fun switchIsOnSale(){
        when{
            this.isOnSale == true -> this.isOnSale = false
            this.isOnSale == false -> this.isOnSale = true
        }
    }

    override fun compareTo(other: Obra): Int {
        when{
            this.nombre > other.nombre-> return 1
            this.nombre < other.nombre-> return -1
            else -> return 0
        }
    }

    override fun toString(): String {
        val enVenta: String
        if (this.isOnSale){
            enVenta = "Si"
        }else{
            enVenta = "No"
        }
        return "Nombre: ${this.nombre}\t\t\t\t" + "Estilo: ${this.estilo}\n" +
                "Altura: ${this.altura}\t\t\t\t" + "Está en venta: $enVenta\n" +
                "Anchura: ${this.anchura}\t\t\t\t" + "ID: ${this.IDObra}\n" +
                "Profundidad: ${this.profundidad}\t\t" + "Precio: $${this.precio}\n\n"
    }
}

/**
 * Clase Usuario
 */
open class Usuario{
    private var user = ""
    private var password = ""
    private var ID = 0

    fun getUser(): String = this.user
    fun getPassword(): String = this.password
    fun getID(): Int = this.ID

    fun setUser(new: String){
        this.user = new
    }
    fun setPassword(new: String){
        this.password = new
    }
    fun setID(new: Int){
        this.ID = new
    }
}

/**
 * Clase Artista
 */
class Artista: Usuario{
    var obras: IList<Obra> = anEmptyList()
    var nombreArtistico: String = ""
    private var totalRecogido: Double = 0.0

    constructor()

    constructor(user: String, password: String, ID: Int, obras: IList<Obra>, nombreArtistico: String, totalRecogido: Double){
        this.setUser(user)
        this.setPassword(password)
        this.setID(ID)
        this.obras = obras
        this.nombreArtistico = nombreArtistico
        this.totalRecogido = totalRecogido
    }

    fun getTotalRecogido(): Double = this.totalRecogido
    fun setNombre_Artistico(new: String){
        this.nombreArtistico = new
    }
    fun setTotalRecogido(new: Double){
        this.totalRecogido = new
    }
}

/**
 * Clase Comprador
 */
class Comprador: Usuario{
    var obras: IList<Obra> = anEmptyList()
    private var nombreComprador: String = ""

    constructor()

    constructor(user: String, password: String, ID: Int, obras: IList<Obra>, nombreComprador: String){
        this.setUser(user)
        this.setPassword(password)
        this.setID(ID)
        this.obras = obras
        this.nombreComprador = nombreComprador
    }

    fun getNombreComprador(): String = this.nombreComprador
    fun setNombreComprador(new: String){
        this.nombreComprador = new
    }
}

/**
 * OBJETO GALERIA
 */
object Galeria{
    val nombre = "WebGalleryOfficial"
    private var dineroReolectado = 0.0
    val artistas: IDictionary<Int, Artista> = emptyDictionary()
    val compradores: IDictionary<Int, Comprador> = emptyDictionary()
    val obras: IDictionary<Int, Obra> = emptyDictionary()
    var loginDataArtist: Artista? = null
    var loginDataBuyer: Comprador? = null

    fun getnombre(): String = this.nombre
    fun getDineroReolectado(): Double = this.dineroReolectado
    fun get_Artistas(): IDictionary<Int, Artista> = this.artistas
    fun get_Compradores(): IDictionary<Int, Comprador> = this.compradores
    fun get_Obras(): IDictionary<Int, Obra> = this.obras

    fun setDineroReolectado(new: Double){
        this.dineroReolectado = new
    }
    fun setArtistLoginData(new: Artista?){
        this.loginDataArtist = new
    }
    fun setBuyerLoginData(new: Comprador?){
        this.loginDataBuyer = new
    }

    /**
     * ARTISTA POR DEFECTO
     */
    fun addDefaultMembers(){
        val defaultArtwork1 = Obra("Homecoming", 1.93, 0.7, 0.05, "Contemporáneo", 349000.0, 9146123, true)
        this.obras.put(defaultArtwork1.IDObra, defaultArtwork1)
        val defaultArtwork2 = Obra("La Caída", 1.2, 0.8, 0.2, "Renacimiento", 177800.0, 9146321, false)
        this.obras.put(defaultArtwork2.IDObra, defaultArtwork2)

        val lstArtist = TList<Obra>()
        lstArtist.add(defaultArtwork1)
        lstArtist.add(defaultArtwork2)
        val lstBuyer = TList<Obra>()
        lstBuyer.add(defaultArtwork2)

        val defaultArtist = Artista("Alezuka", "1234567", 1014309146, lstArtist, "Alex Picasso", 0.0)
        this.artistas.put(defaultArtist.getID(), defaultArtist)

        val defaultBuyer = Comprador("LuksAQ", "AQ1104", 1016113620, lstBuyer, "Lucas Patiño")
        this.compradores.put(defaultBuyer.getID(), defaultBuyer)
    }

    /**
     * ENUNCIADO FINAL
     * Los artistas pueden publicar sus obras en la colección de la galería describiendo las características físicas, el estilo y valor de cada una.
     * Los compradores adquieren las obras vía internet, para lo cual consultan la colección de obras, seleccionan la obra de arte que le interesa y validan el pago correspondiente.
     * El valor final de compra se determina tomando el valor dado por el artista más la comisión del 2 % que gana WebGallery.
     * La galería quiere llevar registro de las obras que ha adquirido un comprador, así como el estado de cada una de las obras de arte (si ha sido vendida o si todavía permanece en la galería).
     */

    /**
     * METODOS DEL CONTROLADOR
     */
    fun addArtist(user: String, password: String,  id: Int, name: String){
        //TODO("Agregar un artista: este método recibe los datos de un artista y lo agrega al diccionario de artista de la galería. Tenga en cuenta que la comisión inicial de un artista es cero y la lista de obras inicia en vacío.\n")

        val artista = Artista(user, password, id, anEmptyList(), name, 0.0)
        if (this.artistas.isEmpty || !this.artistas.containsKey(id)){
            this.artistas.put(id, artista)
        }else if (this.artistas.containsKey(id)){
            throw Exception ("¡El artista con el ID: $id ya existe!")
        }else{
            for ((a, b) in this.artistas){
                if (b.getUser() == user){
                    throw Exception ("¡El nombre de usuario: $user ya existe para el artista $name!")
                }
            }
        }
    }

    fun addBuyer(user: String, password: String,  id: Int, name: String){
        //TODO("Agregar un comprador: este método también recibe los datos de un comprador, crea el objeto y lo agrega al diccionario de compradores de la galería.\n")

        val comprador = Comprador(user, password, id, anEmptyList(), name)
        if (this.compradores.isEmpty || !this.compradores.containsKey(id)){
            this.compradores.put(id, comprador)
        }else if (this.compradores.containsKey(id)){
            throw Exception ("¡El comprador con el ID: $id ya existe!")
        }else{
            for ((a, b) in this.compradores){
                if (b.getUser() == user){
                    throw Exception ("¡El nombre de usuario: $user ya existe  para el comprador $name!")
                }
            }
        }
    }

    fun addArtworkToArtist(idArtist: Int, altura: Double, anchura: Double, profundidad: Double, estilo: String, precio: Double, nombre: String, IDObra: Int){
        //TODO("Agregar una obra a un artista: este método recibe los datos de una obra y la identificación de un artista, y crea la obra y la agrega a la lista de obras del artista. La obra debe tener un estado que indique que está a la venta. Manejar excepción si el artista no existe o la obra está repetida.\n")

        val obra = Obra(nombre, altura, anchura, profundidad, estilo, precio, IDObra, true)

        if (!this.artistas.containsKey(idArtist) || this.artistas.isEmpty){
            throw Exception("El artista con ID: $idArtist NO existe")
        }else{
            for ((a,b) in this.obras) {
                if (obra.compareTo(b) == 0 || this.obras.containsKey(IDObra)) {
                    throw Exception("¡La obra ${obra.nombre} está repetida!, cambie el identificador y/o el nombre")
                }
            }
            val artist = this.artistas[idArtist]
            artist!!.obras.add(obra)
            this.obras.put(obra.IDObra, obra)
        }
    }

    fun getArtworksByArtist(idArtist: Int): IList<Obra>{
        //TODO("Ver las obras de un artista: dada la identificación de un artista, presentar todas las obras que el artista posee. Excepción si el artista no existe.\n")

        val lst: IList<Obra> = TList()
        if (!this.artistas.containsKey(idArtist) || this.artistas.isEmpty){
            throw Exception("El artista con ID: $idArtist NO existe")
        }else{
            val allArtworks = this.artistas[idArtist]!!.obras
            for (i in allArtworks) {
                if (i.isOnSale){
                    lst.add(i)
                }
            }
        }
        if (lst.isEmpty){
            throw Exception ("El artista no posee obras a la venta actualmente")
        }else{
            return lst
        }
    }

    fun getAllArtists(): IDictionary<Int, Artista>{
        //TODO("Ver todos los artistas.\n")
        return this.artistas
    }

    fun getAllBuyers(): IDictionary<Int, Comprador>{
        //TODO("Ver todos los compradores.\n")
        return this.compradores
    }

    fun getPurchasedArtworks(idBuyer: Int): IList<Obra>{
        //TODO("Ver las obras compradas por un comprador: dada la identificación de un comprador, presentar el conjunto de obras que este comprador ha comprado.\n")
        val buyer = this.compradores[idBuyer]
        return buyer!!.obras
    }

    fun getArtistCommission(idArtist: Int): Double{
        //TODO("Ver la comisión ganada por un artista: dada la identificación de un artista, presenta cuánto dinero ha ganado el artista.\n")
            val artist = this.artistas[idArtist]
            return artist!!.getTotalRecogido()
    }

    fun getGalleryCommission(): Double{
        //TODO("Ver la comisión ganada por la galería\n")
        return this.dineroReolectado
    }

    fun toSellArtwork(idArtist: Int, idObra: Int, idBuyer: Int){
        //TODO("Vender una obra de un artista a un comprador: dada la identificación de un artista, la identificación de una obra de ese artista y la identificación del comprador, cambia el estado de la obra a “vendida”, pasa la obra a la lista de obras del comprador, aumenta la comisión del artista y aumenta la comisión de la galería. Lea el enunciado para saber como se maneja este cuento de las comisiones. Excepciones cuando el artista no exista, o el comprador no exista o la obra no exista.\n")
        if (!this.artistas.containsKey(idArtist) || !this.compradores.containsKey(idBuyer) || !this.obras.containsKey(idObra)){
            when{
                !this.artistas.containsKey(idArtist) -> throw Exception("El artista con ID: $idArtist NO existe")
                !this.compradores.containsKey(idBuyer) -> throw Exception("El comprador con ID: $idBuyer NO existe")
                !this.obras.containsKey(idObra) -> throw Exception("La obra con ID: $idObra NO existe")
            }
        }else {
            val artista = this.artistas[idArtist]
            val obra = this.obras[idObra]
            val comprador = this.compradores[idBuyer]

            if (this.getArtworksByArtist(idArtist).contains(obra)){
                obra!!.switchIsOnSale() // Pasa a false (It's not on sale)
                comprador!!.obras.add(obra)
                val comisionArtista = obra.precio
                artista!!.setTotalRecogido(artista.getTotalRecogido() + comisionArtista)
                val comisionGaleria = obra.precio * 0.02
                this.setDineroReolectado(this.dineroReolectado + comisionGaleria)
            }else{
                throw Exception ("La obra ${obra!!.nombre} no pertenece al artista con ID: ${artista!!.getID()}")
            }
        }
    }
}