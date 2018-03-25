package hash.application

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dish.*

/**
 * Created by gouji on 3/1/2018.
 */
class ViewDish : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dish)
        var fav_state = false
        favView.setOnClickListener {
            fav_state = if (fav_state) {
                favView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border_black_24dp))
                false
            } else {
                favView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_red_24dp))
                true
            }
        }

        val bundle: Bundle = intent.getBundleExtra("data")
        if (bundle.isEmpty) {
            Toast.makeText(this, "Failed to find the recipe", Toast.LENGTH_SHORT).show()
            Log.e("log_viewDish", "bundle is empty")
            finish()
        }
        try {
            Picasso.with(this).load(bundle.getString("imageURLs")).into(dishImage)
            textView1.text = bundle.getString("name")
            textView3.text = bundle.getStringArrayList("ingredientLines").joinToString("\n")
            buttonInstruction.setOnClickListener({
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("instructionurl")))
                startActivity(i)
            })

            when {
                bundle.getString("imageURLs") == null -> throw Exception("imageURLs is null")
                bundle.getString("name") == null -> throw Exception("name is null")
                bundle.getString("ingredientLines") == null -> throw Exception("ingredientLines is null")
                bundle.getString("instructionurl") == null -> throw Exception("instructionurl is null")
            }
        } catch (e: Exception) {
            Log.e("log_viewDish", e.toString())
        }

    }

}