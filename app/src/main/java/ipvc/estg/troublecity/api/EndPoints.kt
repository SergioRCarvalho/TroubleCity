package ipvc.estg.troublecity.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface  EndPoints {
    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<user>

    @GET("/users")
    fun getUsers(): Call<List<user>>

    @FormUrlEncoded
    @POST("login")
    fun postLog(@Field("email") email: String, @Field("password") password: String): Call<List<OutputPost>>

}