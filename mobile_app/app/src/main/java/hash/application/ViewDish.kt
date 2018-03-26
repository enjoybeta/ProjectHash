package hash.application

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import hash.application.dataType.Recipe
import hash.application.helpers.FavoriteManager
import kotlinx.android.synthetic.main.activity_viewdish.*

/**
 * Created by gouji on 3/1/2018.
 */
class ViewDish : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewdish)
        val bundle: Bundle = intent.getBundleExtra("data")
        if (bundle.isEmpty) {
            Toast.makeText(this, "Failed to find the recipe", Toast.LENGTH_SHORT).show()
            Log.e("log_viewDish", "bundle is empty")
            finish()
        }

        //setup image and data
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
                bundle.getStringArrayList("ingredientLines") == null -> throw Exception("ingredientLines is null")
                bundle.getString("instructionurl") == null -> throw Exception("instructionurl is null")
            }
        } catch (e: Exception) {
            Log.e("log_viewDish", e.toString())
        }

        //setup the favorite button
        var favState: Boolean = FavoriteManager.findRecipeById(bundle.getString("id"))
        if (favState) {
            favView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_red_24dp))
        } else {
            favView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border_black_24dp))
        }
        favView.setOnClickListener {
            favState = if (favState) {
                //remove the favorite
                favView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border_black_24dp))
                FavoriteManager.removeRecipeByID(bundle.getString("id"))
                false
            } else {
                //add the favorite
                favView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_red_24dp))
                val tmp = Recipe(
                        bundle.getString("name"),
                        bundle.getString("id"),
                        bundle.getInt("totaltime"),
                        bundle.getString("imageURLs"),
                        bundle.getInt("numberofserving"),
                        bundle.getString("flavor"),
                        bundle.getString("instructionurl"),
                        bundle.getStringArrayList("ingredientLines")
                )
                FavoriteManager.addRecipe(tmp)
                true
            }
        }
    }

}