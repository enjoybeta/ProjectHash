package hash.application.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import hash.application.R
import hash.application.dataType.Recipe
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favorite_adapter.view.*

/**
 * Created by gouji on 3/25/2018.
 */
class CustomRecipeAdapter(context: Context, fav: ArrayList<Recipe>) :
        ArrayAdapter<Recipe>(context, R.layout.favorite_adapter, fav) {
    private var data: ArrayList<Recipe> = fav

    private class ViewHolder {
        internal var name: TextView? = null
        internal var image: ImageView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val recipe : Recipe = getItem(position)
        val viewHolder: ViewHolder
        if (convertView == null) {
            viewHolder = ViewHolder()
            view = LayoutInflater.from(context).inflate(R.layout.favorite_adapter, parent, false)
            viewHolder.name = view.recipeName
            viewHolder.image = view.recipeImage
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.name!!.text = recipe.name
        Picasso.with(context).load(recipe.imageURLs).into(viewHolder.image)
        return view!!
    }

    override fun getItem(position: Int): Recipe {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}