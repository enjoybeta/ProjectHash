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
//        val sd = File(context.filesDir,"ingredients.dat")
//        if (!sd.exists()) {
//            Toast.makeText(context, "file not exist", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(context, "file exist", Toast.LENGTH_SHORT).show()
//        }//TODO

        val btnSearch = button
        // set on-click listener
        btnSearch.setOnClickListener {
            Toast.makeText(context, "You clicked on seach.", Toast.LENGTH_SHORT).show()
        }
        super.onStart()
    }
}