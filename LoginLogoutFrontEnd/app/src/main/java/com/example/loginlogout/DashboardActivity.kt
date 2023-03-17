package com.example.loginlogout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnLogout: Button
    private lateinit var userName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnLogout = findViewById(R.id.btnLogout)
        userName = intent.getStringExtra(KEY_USERNAME).toString()

        val welcomeText = "Welcome $userName!"
        val tvWelcome:TextView = findViewById(R.id.tvWelcome)
        tvWelcome.text = welcomeText

        val tvHidden:TextView = findViewById(R.id.tvLink)
        if (intent.getBooleanExtra("isManager", false)){
            tvHidden.visibility = View.VISIBLE
        } else {
            tvHidden.visibility = View.GONE
        }

        btnLogout.setOnClickListener { logoutUser() }
    }

    private fun logoutUser(){
        val call: Call<ResponseBody> = RetrofitClient.getInstance().api.logoutUser(userName)

        call.enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(this@DashboardActivity, "User Logged out!", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(this@DashboardActivity, MainActivity::class.java))
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@DashboardActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}