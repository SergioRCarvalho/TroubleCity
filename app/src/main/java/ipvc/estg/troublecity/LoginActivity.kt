package ipvc.estg.troublecity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ipvc.estg.troublecity.api.EndPoints
import ipvc.estg.troublecity.api.OutputPost
import ipvc.estg.troublecity.api.ServiceBuilder
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var  etPassword: EditText
    val MIN_PASSWORD_LENGTH = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
      //  viewInitializations()
    }

    fun viewInitializations() {
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        // To show back button in actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Checking if the input in form is valid
    fun validateInput(): Boolean {
        if (etEmail.text.toString() == "") {
            etEmail.error = "Please Enter Email"
            return false
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Please Enter Password"
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.error = "Please Enter Valid Email"
            return false
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.error = "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Hook Click Event
    fun performSignUp(view: View) {

        var email = findViewById<EditText>(R.id.et_email)
        var pass = findViewById<EditText>(R.id.et_password)
        val intent = Intent(this, MainActivity::class.java)

        if(email.text.isNullOrEmpty() || pass.text.isNullOrEmpty()){
        }
        else{
            val request = ServiceBuilder.buildService(EndPoints::class.java)
            val call = request.postLog(email.text.toString(), pass.text.toString())
            call.enqueue(object : Callback<List<OutputPost>>{
                override fun onResponse(call: Call<List<OutputPost>>, response: Response<List<OutputPost>>) {
                    if (response.isSuccessful){
                        for(OutputPost in response.body()!!){
                            //Shared Preferences Login
                            val sharedPref: SharedPreferences = getSharedPreferences(
                                getString(R.string.ofShared), Context.MODE_PRIVATE
                            )
                            with(sharedPref.edit()){
                                putBoolean(getString(R.string.onShared), true)
                                putInt(getString(R.string.id), OutputPost.id)
                                commit()
                            }
                        }
                        Toast.makeText(this@LoginActivity, getString(R.string.login_sucesso), Toast.LENGTH_SHORT).show()
                        //      startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<List<OutputPost>>, t: Throwable) {
                   Toast.makeText(this@LoginActivity, getString(R.string.login_erro), Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

}