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

}