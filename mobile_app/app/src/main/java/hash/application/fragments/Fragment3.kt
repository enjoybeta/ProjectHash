package hash.application.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hash.application.activities.AddIngredientActivity
import hash.application.R
import hash.application.dataType.Ingredient
import hash.application.helpers.CustomIngredientAdapter
import hash.application.managers.IngredientManager
import kotlinx.android.synthetic.main.fragment3.*

//"ingredients" fragment
class Fragment3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment3, container, false)
    }

    override fun onStart() {
        super.onStart()
        val values: ArrayList<Ingredient> = IngredientManager.getList()
        val adapter = CustomIngredientAdapter(activity!!, values)
        if (values.isEmpty()) {
            Toast.makeText(context, "No data stored", Toast.LENGTH_LONG).show()
            Log.e("log_fragment4", "no ingredients")
        }
        ingredientList.adapter = adapter// Assign adapter to ListView
        // click on a specific ingredient to reduce the amount by 1
        ingredientList.setOnItemClickListener { _, _, position, _ ->
            val ing: Ingredient = adapter.getItem(position)
            IngredientManager.reduceIngredientByName(ing.name)
            adapter.notifyDataSetChanged()
        }
        // long click to remove ingredient
        ingredientList.setOnItemLongClickListener { _, _, position, _ ->
            val ing: Ingredient = adapter.getItem(position)
            IngredientManager.removeIngredientByName(ing.name)
            adapter.notifyDataSetChanged()
            true
        }

        button1.setOnClickListener{
            val intent = Intent(context, AddIngredientActivity::class.java)
            startActivity(intent)
        }
    }

}