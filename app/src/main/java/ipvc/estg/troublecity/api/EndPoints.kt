package ipvc.estg.troublecity.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface  EndPoints {
    @GET("api/user/{id}")
    fun getUserById(@Path("id") id: Int): Call<utilizador>

    @GET("api/users/")
    fun getUsers(): Call<List<utilizador>>

    @FormUrlEncoded
    @POST("api/login")
    fun postLog(@Field("telemovel") telemovel: String, @Field("password") password: String): Call<List<OutputPost>>

}