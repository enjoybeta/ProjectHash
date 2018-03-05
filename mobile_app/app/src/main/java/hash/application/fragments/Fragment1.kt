package hash.application.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import hash.application.R
import kotlinx.android.synthetic.main.fragment1.*
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import android.content.Intent
import hash.application.ViewDish


//"home" fragment
class Fragment1: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment1, container, false)
    }

    override fun onStart() {
        searchview.queryHint = "Search anything you like"
        retrievePhoto()

        imageView1.setOnClickListener(View.OnClickListener{
            Toast.makeText(context, "clicked on image1", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ViewDish::class.java)
            startActivity(intent)
        })
        super.onStart()
    }

    private fun retrievePhoto() {
        Picasso.with(activity).load("https://cdn.shopify.com/s/files/1/1190/4748/t/10/assets/logo.png").into(imageView3);
        Picasso.with(activity).load(R.drawable.image_item4).into(imageView4);
    }

}