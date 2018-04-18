package hash.application.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import hash.application.managers.UserManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hash.application.R
import hash.application.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment5.*

//"profile" fragment
class Fragment5 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment5, container, false)
    }

    override fun onStart() {
        super.onStart()
        textView6.text = UserManager.getUsername()

        // Sign in or Register
        if (UserManager.getLoginState()) {
            btn_login.text = "Logout"
            val btnLogin = btn_login
            btnLogin.setOnClickListener {
                Toast.makeText(context, "DEBUG: Clicked on logout.", Toast.LENGTH_SHORT).show()
                UserManager.changeLoginState(false)
                UserManager.setUsername("Guest")
                val ft = fragmentManager!!.beginTransaction()
                ft.detach(this).attach(this).commit()
            }
            btn_upload.visibility = View.VISIBLE
            btn_download.visibility = View.VISIBLE
        } else {
            btn_login.text = "Login"
            val btnLogin = btn_login
            btnLogin.setOnClickListener {
                Toast.makeText(context, "DEBUG: Clicked on Login.", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
            btn_upload.visibility = View.GONE
            btn_download.visibility = View.GONE
        }
        // Download data from server
        val btnDownload = btn_download
        btnDownload.setOnClickListener {
            Toast.makeText(context, "DEBUG: Clicked on Download data.", Toast.LENGTH_SHORT).show()
        }
        // Upload data to server
        val btnUpload = btn_upload
        btnUpload.setOnClickListener {
            Toast.makeText(context, "DEBUG: Clicked on Upload data.", Toast.LENGTH_SHORT).show()
        }
    }
}