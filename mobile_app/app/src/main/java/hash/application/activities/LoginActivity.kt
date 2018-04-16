package hash.application.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import hash.application.R
import android.content.Intent
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)//initalize layout
    }

    override fun onStart() {
        // jump to account register page
        val btnToSignup = button9
        btnToSignup.setOnClickListener{
            Toast.makeText(this, "DEBUG: Jump to sign up page.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        super.onStart()
    }
}