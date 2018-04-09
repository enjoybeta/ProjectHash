package hash.application.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import hash.application.R
import android.widget.TextView
import hash.application.dataType.Ingredient
import kotlinx.android.synthetic.main.ingredient_adapter.view.*

/**
 * Created by gouji on 3/25/2018.
 */
class CustomIngredientAdapter(context: Context, ing: ArrayList<Ingredient>) :
        ArrayAdapter<Ingredient>(context, R.layout.ingredient_adapter, ing) {
    private var data: ArrayList<Ingredient> = ing

    private class ViewHolder {
        internal var name: TextView? = null
        internal var quantity: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val ingredient: Ingredient = getItem(position)
        val viewHolder: ViewHolder
        if (convertView == null) {
            viewHolder = ViewHolder()
            view = LayoutInflater.from(context).inflate(R.layout.ingredient_adapter, parent, false)
            viewHolder.name = view.ingredientName
            viewHolder.quantity = view.ingredientQuantity
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.name!!.text = ingredient.name
        viewHolder.quantity!!.text = ingredient.quantity.toString() + " " + ingredient.unit
        return view!!
    }

    override fun getItem(position: Int): Ingredient {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}