package hash.application.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hash.application.R
import hash.application.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment5.*

//"profile" fragment
class Fragment5: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment5, container, false)
    }

    override fun onStart() {
        // Sign in or Register
        val btnLogin = button4
        btnLogin.setOnClickListener{
            Toast.makeText(context, "DEBUG: Clicked on Login.", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
        super.onStart()
    }
}