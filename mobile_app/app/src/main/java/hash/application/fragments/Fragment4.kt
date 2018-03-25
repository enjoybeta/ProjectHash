package hash.application.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hash.application.R
import android.widget.ArrayAdapter
import android.widget.Toast
import hash.application.helpers.FavoriteManager
import kotlinx.android.synthetic.main.fragment4.*

//"favorite" fragment
class Fragment4 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment4, container, false)
    }

    override fun onStart() {
        val values: ArrayList<String> = getFavorites()
        val adapter = ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_2, android.R.id.text1, values)
        if (values.isEmpty()) {
            Toast.makeText(context, "No data stored", Toast.LENGTH_LONG).show()
        }
        list.adapter = adapter// Assign adapter to ListView

        super.onStart()
    }

    private fun getFavorites(): ArrayList<String> {
        FavoriteManager.initFromFile(context!!.filesDir)
        return FavoriteManager.getNameList()
    }
}