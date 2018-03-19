package hash.application

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dish.*

/**
 * Created by gouji on 3/1/2018.
 */
class ViewDish : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dish)

        val bundle : Bundle = intent.getBundleExtra("data")
        if(bundle.isEmpty){
            Toast.makeText(this, "Failed to find the recipe", Toast.LENGTH_SHORT).show()
            finish()
        }
        Picasso.with(this).load(bundle.getString("imageURLs")).into(dishImage)
        textView1.text = bundle.getString("name")
        textView3.text = bundle.getStringArrayList("ingredientLines").joinToString("\n")
        textView5.text = bundle.getString("instructionurl")
    }

}