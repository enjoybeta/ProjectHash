package hash.application.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import hash.application.R
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)//initalize layout
    }

    override fun onStart() {
        // jump to account register page
        val btnToLogin = button7
        btnToLogin.setOnClickListener{
            Toast.makeText(this, "DEBUG: Jump to login page.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        super.onStart()
    }
}