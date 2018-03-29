package hash.application.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hash.application.R
import kotlinx.android.synthetic.main.fragment2.*
import android.widget.Toast
import hash.application.SearchActivity
import hash.application.dataType.SearchCoarse
import hash.application.helpers.WebManager

//"suggestion" fragment
class Fragment2: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onStart() {
        val btnSearch = button
        // set on-click listener for search with conditions
        btnSearch.setOnClickListener {
            Toast.makeText(context, "You clicked on search.", Toast.LENGTH_SHORT).show()
            var str = "[]"
            // get recipes from server using WebManager
            val webThread = Thread(Runnable {
                val tmp = SearchCoarse(editText.text.toString().toInt())
                str = WebManager.searchCoarse(tmp)
            })
            webThread.start()
            webThread.join()
            // call SearchActivity to process returned string from WebManager
            val intent = Intent(context, SearchActivity::class.java)
            intent.putExtra("json", str)
            startActivity(intent)
        }
        super.onStart()
    }
}