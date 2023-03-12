package com.example.loginlogout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val welcomeText = "Welcome " + intent.getStringExtra("username") + "!"
        val tvWelcome:TextView = findViewById(R.id.tvWelcome)
        tvWelcome.text = welcomeText

        val tvHidden:TextView = findViewById(R.id.tvLink)
        if (intent.getBooleanExtra("isManager", false)){
            tvHidden.visibility = View.VISIBLE
        } else {
            tvHidden.visibility = View.GONE
        }

        btnLogout = findViewById<Button?>(R.id.btnLogout)
        btnLogout.setOnClickListener { logoutUser() }
    }

    private fun logoutUser(){
        //todo
    }

}