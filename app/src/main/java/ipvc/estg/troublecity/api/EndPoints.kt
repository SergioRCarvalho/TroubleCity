package ipvc.estg.troublecity.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface  EndPoints {
    @GET("user/{id}")
    fun getUserById(@Path("id") id: Int): Call<utilizador>

    @GET("users")
    fun getUsers(): Call<List<utilizador>>

    @FormUrlEncoded
    @POST("login")
    fun postLog(@Field("email") email: String, @Field("password") password: String): Call<List<OutputPost>>

    @GET("markers")
    fun getPontos(): Call<List<Markers>>

    @FormUrlEncoded
    @POST("addreport")
    fun postProblem(@Field("id_utilizador") id_utilizador: Int, @Field("descricao") descricao: String, @Field("local") local: String, @Field("data") data: String, @Field("lat") lat: String, @Field("lng") lng: String, @Field("img") img: String): Call<Markers>

    @FormUrlEncoded
    @PUT("editReport")
    fun updateProblem(@Field("id_utilizador") userId: Int?, @Field("lat") lat: String?,@Field("lng") long: String?,@Field("descricao") problem: String?,@Field("id") id: Int?): Call<Markers>

}