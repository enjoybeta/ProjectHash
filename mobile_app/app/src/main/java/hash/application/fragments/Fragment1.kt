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
import android.widget.ArrayAdapter
import hash.application.ViewDish
import android.support.v7.widget.SearchView

//"home" fragment
class Fragment1: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment1, container, false)
    }

    override fun onStart() {
        retrievePhoto()

        imageView1.setOnClickListener({
            Toast.makeText(context, "clicked on image1", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ViewDish::class.java)
            startActivity(intent)
        })
        provideSearch()
        super.onStart()
    }

    private fun retrievePhoto() {
        Picasso.with(activity).load("https://cdn.shopify.com/s/files/1/1190/4748/t/10/assets/logo.png").into(imageView3);
        Picasso.with(activity).load(R.drawable.image_item4).into(imageView4);
    }

    private fun provideSearch(){
//        val tmp = arrayOf("a", "b", "c")
//        val adapter = ArrayAdapter<String>(context,
//                android.R.layout.simple_list_item_1, android.R.id.text1, tmp)
        searchview.setIconifiedByDefault(true)
        searchview.setFocusable(false)
        searchview.clearFocus()
        searchview.queryHint = "Any recipes"

        searchview.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(context, "onQueryTextChange:$newText", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, "onQueryTextSubmit:$query", Toast.LENGTH_SHORT).show()
                return true
            }
        })
    }

}
