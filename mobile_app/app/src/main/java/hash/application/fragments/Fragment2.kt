package hash.application.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hash.application.R
import kotlinx.android.synthetic.main.fragment2.*
import android.widget.Toast

//"suggestion" fragment
class Fragment2: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onStart() {
        val btnSearch = button
        // set on-click listener
        btnSearch.setOnClickListener {
            Toast.makeText(context, "You clicked on search.", Toast.LENGTH_SHORT).show()
            //val intent = Intent(context, ?::class.java)//TODO
            //intent.putExtra("?", ?)
            //startActivity(intent)
        }
        super.onStart()
    }
}