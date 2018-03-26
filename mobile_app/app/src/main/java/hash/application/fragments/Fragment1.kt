package hash.application.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import hash.application.R
import kotlinx.android.synthetic.main.fragment1.*
import android.widget.Toast
import android.content.Intent
import hash.application.SearchActivity
import hash.application.ViewDish
import hash.application.helpers.WebManager
import com.google.gson.Gson
import hash.application.dataType.Recipe

//"home" fragment
class Fragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onStart() {
        super.onStart()

        setupTodaySuggestion()
        provideSearch()
    }

    private fun setupTodaySuggestion() {
        var recipe1 : Recipe? = null
        var recipe2 : Recipe? = null
        var recipe3 : Recipe? = null
        var recipe4 : Recipe? = null
        val webThread = Thread(Runnable {
            val dataStr1 : String = WebManager.getToday1()
            val dataStr2 : String = WebManager.getToday2()
            val dataStr3 : String = WebManager.getToday3()
            val dataStr4 : String = WebManager.getToday4()
            recipe1 = Gson().fromJson(dataStr1, Recipe::class.java)
            recipe2 = Gson().fromJson(dataStr2, Recipe::class.java)
            recipe3 = Gson().fromJson(dataStr3, Recipe::class.java)
            recipe4 = Gson().fromJson(dataStr4, Recipe::class.java)
        })
        webThread.start()
        webThread.join()
        Picasso.with(activity).load(recipe1!!.imageURLs).into(imageView1)
        Picasso.with(activity).load(recipe2!!.imageURLs).into(imageView2)
        Picasso.with(activity).load(recipe3!!.imageURLs).into(imageView3)
        Picasso.with(activity).load(recipe4!!.imageURLs).into(imageView4)
        imageView1.setOnClickListener({
            val intent = Intent(context, ViewDish::class.java)
            val bundle = getRecipeBundle(recipe1!!)
            intent.putExtra("data",bundle)
            startActivity(intent)
        })
        imageView2.setOnClickListener({
            val intent = Intent(context, ViewDish::class.java)
            val bundle = getRecipeBundle(recipe2!!)
            intent.putExtra("data",bundle)
            startActivity(intent)
        })
        imageView3.setOnClickListener({
            val intent = Intent(context, ViewDish::class.java)
            val bundle = getRecipeBundle(recipe3!!)
            intent.putExtra("data",bundle)
            startActivity(intent)
        })
        imageView4.setOnClickListener({
            val intent = Intent(context, ViewDish::class.java)
            val bundle = getRecipeBundle(recipe4!!)
            intent.putExtra("data",bundle)
            startActivity(intent)
        })
    }

    private fun provideSearch() {
        searchView.setIconifiedByDefault(true)
        searchView.isFocusable = false
        searchView.clearFocus()
        searchView.queryHint = "Any recipe"
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(text: String): Boolean {
                Toast.makeText(context, "onQueryTextChange:[$text]", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, "onQueryTextSubmit:$query", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, SearchActivity::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
                return true
            }
        })
    }

    private fun getRecipeBundle(recipe: Recipe) : Bundle{
        val bundle = Bundle()
        bundle.putString("name", recipe.name)
        bundle.putString("id", recipe.id)
        bundle.putInt("totaltime", recipe.totaltime)
        bundle.putString("imageURLs", recipe.imageURLs)
        bundle.putInt("numberofserving", recipe.numberofserving)
        bundle.putString("flavor", recipe.flavor)
        bundle.putString("instructionurl", recipe.instructionurl)
        bundle.putStringArrayList("ingredientLines", recipe.ingredientLines)
        return bundle
    }
}

