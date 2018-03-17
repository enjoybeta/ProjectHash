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
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.hash.application.serverAddress
import hash.application.SearchActivity
import hash.application.ViewDish

//"home" fragment
class Fragment1: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment1, container, false)
    }

    override fun onStart() {
        retrievePhoto()
        provideSearch()

        imageView1.setOnClickListener({
            Toast.makeText(context, "clicked on image1", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ViewDish::class.java)
            startActivity(intent)
        })

        super.onStart()
    }

    private fun retrievePhoto() {
        Picasso.with(activity).load("https://cdn.shopify.com/s/files/1/1190/4748/t/10/assets/logo.png").into(imageView3);
        Picasso.with(activity).load(R.drawable.image_item4).into(imageView4)
        serverAddress+"/today1".httpGet().responseString { request, response, result ->
            when (result) {
                is Result.Failure -> {
                    val error = result.getException()
                    print(error)
                }
                is Result.Success -> {
                    val data = result.get()
                    print(data)
                }
            }
        }
    }

    private fun provideSearch(){
        searchView.setIconifiedByDefault(true)
        searchView.isFocusable = false
        searchView.clearFocus()
        searchView.queryHint = "Any recipe"
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(text: String): Boolean {
                Toast.makeText(context, "onQueryTextChange:[$text]", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, "onQueryTextSubmit:$query", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, SearchActivity::class.java)
                intent.putExtra("query",query)
                startActivity(intent)
                return true
            }
        })
    }

}
