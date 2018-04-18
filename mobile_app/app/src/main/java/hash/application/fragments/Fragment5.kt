package hash.application.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import hash.application.R
import hash.application.activities.LoginActivity
import hash.application.dataType.User
import hash.application.managers.*
import kotlinx.android.synthetic.main.fragment5.*
import java.io.File

//"profile" fragment
class Fragment5 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment5, container, false)
    }

    override fun onStart() {
        super.onStart()
        textView6.text = UserManager.getUsername()

        // Check if user has signed in
        if (UserManager.getLoginState()) {
            btn_login.text = "Logout"
            val btnLogin = btn_login
            btnLogin.setOnClickListener {
//                Toast.makeText(context, "DEBUG: Clicked on logout.", Toast.LENGTH_SHORT).show()
                UserManager.changeLoginState(false)
                UserManager.setUsername("Guest")
                val ft = fragmentManager!!.beginTransaction()
                ft.detach(this).attach(this).commit()
            }
            // allow user to upload or download data after signed in
            btn_upload.visibility = View.VISIBLE
            btn_download.visibility = View.VISIBLE
        } else {
            btn_login.text = "Login"
            val btnLogin = btn_login
            btnLogin.setOnClickListener {
//                Toast.makeText(context, "DEBUG: Clicked on Login.", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
            // hide buttons after logged out
            btn_upload.visibility = View.GONE
            btn_download.visibility = View.GONE
        }
        // Download data from server
        val btnDownload = btn_download
        lateinit var servResult: String
        btnDownload.setOnClickListener {
            // connect to server
            val webThread = Thread(Runnable {
                val tmp = UserManager.getUser()
                servResult = WebManager.downloadData(tmp)
            })
            webThread.start()
            webThread.join()
            // check result returned from server
            if (servResult == "Wrong username or password!") {
                Toast.makeText(context, servResult, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(context, "Download Success!", Toast.LENGTH_SHORT).show()
            // save data downloaded from server
            val tmpUser = Gson().fromJson(servResult, User::class.java)
            FileManager(context!!.filesDir, "favorites.dat").writeFile(tmpUser.favoriteStr)
            FileManager(context!!.filesDir, "ingredients.dat").writeFile(tmpUser.ingredientStr)
            FavoriteManager.initFromFile(context!!.filesDir)
            IngredientManager.initFromFile(context!!.filesDir)
    }
        // Upload data to server
        val btnUpload = btn_upload
        btnUpload.setOnClickListener {
            // read stored data
            val tmp = UserManager.getUser()
            val tmp1 = FileManager(context!!.filesDir,"ingredients.dat")
            tmp1.proofFile()
            tmp.ingredientStr = tmp1.readFile()
            val tmp2 = FileManager(context!!.filesDir,"favorites.dat")
            tmp2.proofFile()
            tmp.favoriteStr = tmp2.readFile()
            // connect to server
            val webThread = Thread(Runnable {
                servResult = WebManager.uploadData(tmp)
            })
            webThread.start()
            webThread.join()
            Toast.makeText(context, servResult, Toast.LENGTH_SHORT).show()
        }
    }
}