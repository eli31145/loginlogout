package com.example.loginlogout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


//Communicates with Spring Backend using retrofit client
class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)

        findViewById<Button>(R.id.btnLogin).setOnClickListener { loginUser() }

        /*findViewById<Button>(R.id.btnRegister).setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }*/
    }

    private fun loginUser(){
        val userName = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (userName.isEmpty()){
            etUsername.error = "Username is required"
            etUsername.requestFocus()
            return
        } else if (password.isEmpty()){
            etPassword.error = "Password is required"
            etPassword.requestFocus()
            return
        }

        val call: Call<ResponseBody> = RetrofitClient.getInstance().api.checkUser(User(userName, password))
        call.enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var s = ""
                try {
                    s = response.body().toString()
                } catch (ioE:IOException){
                    ioE.printStackTrace()
                }

                if (s.equals(userName)) {
                    Toast.makeText(this@LoginActivity, "User Logged in!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java).putExtra("username", userName))
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid userid or password", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    }
}