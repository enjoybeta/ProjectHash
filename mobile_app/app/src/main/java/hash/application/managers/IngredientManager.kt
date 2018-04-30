package hash.application.managers

import android.util.Log
import com.google.gson.Gson
import hash.application.dataType.Ingredient
import hash.application.dataType.Ingredients
import java.io.File

/**
 * Created by gouji on 3/26/2018.
 */
//use Singleton by object
object IngredientManager {
    private lateinit var ing: Ingredients
    private lateinit var dir: File
    private const val fileName = "ingredients.dat"

    fun initFromFile(_dir: File) {
        ing = Ingredients()
        dir = _dir
        val dataFile = FileManager(dir, fileName)
        if (!dataFile.checkFile()) {
            dataFile.proofFile()
        }
        try {
            val rawString = dataFile.readFile()
            val ingredients: Ingredients = Gson().fromJson(rawString, Ingredients::class.java)
            if (ingredients.ingredients == null) {//catch parsing failure, recipes could be null
                Log.e("log_IngredientManager", "ingredient is null")
                throw Exception("ingredient is null")
            }
            for (i in ingredients.ingredients) {
                addIngredient(i)
            }
        } catch (e: Exception) {
            dataFile.proofFile()
        }
    }

    private fun saveToFile() {
        val dataFile = FileManager(dir, fileName)
        val jsonStr: String = Gson().toJson(ing)
        dataFile.writeFile(jsonStr)
    }

    fun addIngredient(ingredient: Ingredient): Boolean {
        val ret = ing.ingredients.add(ingredient)
        saveToFile()
        return ret
    }

    fun reduceIngredientByName(id: String): Boolean {
        for (i in ing.ingredients) {
            if (i.name == id) {
                if (i.quantity <= 1) {
                    val ret = ing.ingredients.remove(i)
                    saveToFile()
                    return ret
                }
                else {
                    i.quantity -= 1
                    saveToFile()
                    return true
                }
            }
        }
        return false
    }

    fun removeIngredientByName(id: String): Boolean {
        for (i in ing.ingredients) {
            if (i.name == id) {
                val ret = ing.ingredients.remove(i)
                saveToFile()
                return ret
            }
        }
        return false
    }

    fun findIngredientByName(name: String): Boolean {
        for (i in ing.ingredients) {
            if (i.name == name) {
                return true
            }
        }
        return false
    }

    fun getNameList(): ArrayList<String> {
        val ret: ArrayList<String> = ArrayList()
        for (i in ing.ingredients) {
            ret.add(i.name)
        }
        return ret
    }

    fun getList(): ArrayList<Ingredient> {
        return ing.ingredients
    }
}
