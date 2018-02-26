package hash.application.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTabHost
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hash.application.R
import hash.application.helpers.FileManager

//"ingredients" fragment
class Fragment3 : Fragment() {
    private var dataFile: FileManager? = null
    private var host: FragmentTabHost? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment3, container, false)
    }

//        dataFile = FileManager(context.filesDir,"ingredients.dat")
//        dataFile!!.proofFile()
}