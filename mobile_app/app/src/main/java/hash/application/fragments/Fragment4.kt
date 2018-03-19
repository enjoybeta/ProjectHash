package hash.application.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hash.application.R
import hash.application.helpers.FileManager
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.gson.Gson
import hash.application.dataType.Favorites
import kotlinx.android.synthetic.main.fragment4.*

//"favorite" fragment
class Fragment4 : Fragment() {
    private var dataFile: FileManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment4, container, false)
    }

    override fun onStart() {
        val values: ArrayList<String> = readFavoriteFile()
        val adapter = ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_2, android.R.id.text1, values)
        if (values.isEmpty()){
            Toast.makeText(context, "No data stored", Toast.LENGTH_LONG).show()
        }
        list.adapter = adapter// Assign adapter to ListView

        super.onStart()
    }

    private fun readFavoriteFile(): ArrayList<String> {
        dataFile = FileManager(context!!.filesDir, "favorites.dat")
        if (!dataFile!!.checkFile()) {
            dataFile!!.proofFile()
            return ArrayList()
        }
        val rawString = dataFile!!.readFile()
        var favorites = Gson().fromJson(rawString, Favorites.DishList::class.java)
        if (favorites.dishes == null) {//catch parsing failure
            return ArrayList()
        }
        val tmp: ArrayList<String> = ArrayList()
        for (i in favorites.dishes) {
            tmp.add(i.name)
        }
        return tmp
    }
}