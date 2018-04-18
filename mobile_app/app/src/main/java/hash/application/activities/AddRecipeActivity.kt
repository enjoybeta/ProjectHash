package hash.application.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import hash.application.R
import hash.application.dataType.Ingredient
import hash.application.managers.IngredientManager
import kotlinx.android.synthetic.main.activity_add_recipe.*

class AddRecipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        val unitArray = arrayOf("", "Bag", "Basket", "Bottle", "Cup", "Teaspoon")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, unitArray)

        button_add.setOnClickListener {
            if(editText1.text.toString() == ""){
                Toast.makeText(this, "Need ingredient name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val tmpIngredient = Ingredient(editText1.text.toString(), Integer.parseInt(editText2.text.toString()), spinner.selectedItem.toString())
            IngredientManager.addIngredient(tmpIngredient)
            finish()
        }
    }
}
