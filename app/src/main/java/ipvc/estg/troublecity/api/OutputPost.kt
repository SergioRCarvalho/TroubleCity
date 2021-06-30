package ipvc.estg.troublecity.api

data class OutputPost (
    val id:Int,
    val nome:String,
    val email:String,
    val password:String)

data class Markers(
    val id:Int,
    val id_utilizador: Int,
    val descricao:String,
    val local:String,
    val data: String,
    val lat: Double,
    val lng: Double,
    val img: String)