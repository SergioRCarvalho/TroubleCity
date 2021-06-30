package ipvc.estg.troublecity.api

import java.util.*

data class utilizador(
    val id:Int,
    val nome:String,
    val telemovel:Int,
    val password:String
  //  val email:String
)
data class tipo_nota(
    val id:Int,
    val nome_nota:String
)

data class nota(
    val id:Int,
    val id_tipo_nota:tipo_nota,
    val id_utilizador:utilizador,
    val desc:String,
    val local:String,
    val data: Date
)