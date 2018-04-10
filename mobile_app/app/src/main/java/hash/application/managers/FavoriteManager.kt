package hash.application.managers

import android.util.Log
import com.google.gson.Gson
import hash.application.dataType.Recipes
import hash.application.dataType.Recipe
import java.io.File
import java.util.ArrayList

/**
 * Created by gouji on 3/25/2018.
 */
//use Singleton by object
object FavoriteManager {
    private val fav: Recipes = Recipes()
    private var dir: File? = null
    private const val fileName = "favorites.dat"

    fun initFromFile(_dir: File) {
        dir = _dir
        val dataFile = FileManager(dir!!, fileName)
        if (!dataFile.checkFile()) {
            dataFile.proofFile()
        }
        try {
            val rawString = dataFile.readFile()
            val favorites: Recipes = Gson().fromJson(rawString, Recipes::class.java)
            if (favorites.recipes == null) {//catch parsing failure, recipes could be null
                Log.e("log_FavoriteManager", "recipes is null")
                throw Exception("recipes is null")
            }
            for (i in favorites.recipes) {
                addRecipe(i)
            }
        } catch (e: Exception) {
            dataFile.proofFile()
        }
    }

    private fun saveToFile() {
        val dataFile = FileManager(dir!!, fileName)
        val jsonStr: String = Gson().toJson(fav)
        dataFile.writeFile(jsonStr)
    }

    fun addRecipe(recipe: Recipe): Boolean {
        val ret = fav.recipes.add(recipe)
        saveToFile()
        return ret
    }

    fun removeRecipeByID(id: String): Boolean {
        for (i in fav.recipes) {
            if (i.id == id) {
                val ret = fav.recipes.remove(i)
                saveToFile()
                return ret
            }
        }
        return false
    }

    fun findRecipeByName(name: String): Boolean {
        for (i in fav.recipes) {
            if (i.name == name) {
                return true
            }
        }
        return false
    }

    fun findRecipeById(id: String): Boolean {
        for (i in fav.recipes) {
            if (i.id == id) {
                return true
            }
        }
        return false
    }

    fun getNameList(): ArrayList<String> {
        val ret: ArrayList<String> = ArrayList()
        for (i in fav.recipes) {
            ret.add(i.name)
        }
        return ret
    }

    fun getList(): ArrayList<Recipe> {
        return fav.recipes
    }
}