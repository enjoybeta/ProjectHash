package hash.application.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import hash.application.R
import android.content.Intent
import hash.application.dataType.User
import hash.application.managers.UserManager
import hash.application.managers.WebManager
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)//initialize layout
    }

    override fun onStart() {
        super.onStart()
        // jump to account register page
        val btnToSignup = button9
        btnToSignup.setOnClickListener{
//            Toast.makeText(this, "DEBUG: Jump to sign up page.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        // send entered user info to server and check if user exists
        lateinit var servResult: String
        val btnLogin = button5
        btnLogin.setOnClickListener {
//            Toast.makeText(this, "DEBUG: Clicked on login.", Toast.LENGTH_SHORT).show()
            // connect to server
            val webThread = Thread(Runnable {
                val user = User(editText4.text.toString(), editText5.text.toString(), "", "")
                servResult = WebManager.userLogin(user)
            })
            webThread.start()
            webThread.join()
            // check result returned by server
            if (servResult == "Login success!") {
                UserManager.setUsername(editText4.text.toString())
                UserManager.setPassword(editText5.text.toString())
                UserManager.changeLoginState(true)
                finish()
            }
            else {
                Toast.makeText(this, servResult, Toast.LENGTH_SHORT).show()
            }
        }
    }
}