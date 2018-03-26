package hash.application.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hash.application.R
import android.widget.Toast
import hash.application.ViewDish
import hash.application.dataType.Recipe
import hash.application.helpers.CustomRecipeAdapter
import hash.application.helpers.FavoriteManager
import kotlinx.android.synthetic.main.fragment4.*

//"favorite" fragment
class Fragment4 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment4, container, false)
    }

    override fun onStart() {
        val values: ArrayList<Recipe> = getFavorites()
        val adapter = CustomRecipeAdapter(activity!!,values)
        if (values.isEmpty()) {
            Toast.makeText(context, "No data stored", Toast.LENGTH_LONG).show()
            Log.e("log_fragment4", "no favorites")
        }
        favList.adapter = adapter// Assign adapter to ListView
        favList.setOnItemClickListener { _, _, position, id ->
            val recipe: Recipe = adapter.getItem(position)
            val intent = Intent(context, ViewDish::class.java)
            val bundle = recipe.getRecipeBundle()
            intent.putExtra("data",bundle)
            startActivity(intent)
        }
        super.onStart()
    }

    private fun getFavorites(): ArrayList<Recipe> {
        FavoriteManager.initFromFile(context!!.filesDir)
        return FavoriteManager.getList()
    }
}