package com.example.loginlogout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

//Communicates with Spring Backend using retrofit client
class RegisterActivity : AppCompatActivity() {

    private lateinit var etRUserName: EditText
    private lateinit var etRPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etRUserName = findViewById(R.id.etRUserName)
        etRPassword = findViewById(R.id.etRPassword)

        findViewById<Button>(R.id.btnRegister).setOnClickListener { registerUser() }

        findViewById<TextView>(R.id.tvLoginLink).setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun registerUser(){
        val newUserName = etRUserName.text.toString().trim()
        val newPassword = etRPassword.text.toString().trim()

        if(newUserName.isEmpty()){
            etRUserName.error = "Username is required"
            etRUserName.requestFocus()
            return
        } else if (newPassword.isEmpty()){
            etRPassword.error = "Password is required"
            etRPassword.requestFocus()
            return
        }

        val call: Call<ResponseBody> = RetrofitClient.getInstance().api.createUser(User(newUserName, newPassword))
        call.enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (ioE: IOException) {
                    ioE.printStackTrace()
                }

                if (s.equals("SUCCESS")){
                    Toast.makeText(this@RegisterActivity, "Successfully registered. Please login", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                } else {
                    Toast.makeText(this@RegisterActivity, "Received $s", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

}