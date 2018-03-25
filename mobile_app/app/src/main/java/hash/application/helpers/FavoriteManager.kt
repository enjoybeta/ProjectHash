package hash.application.helpers

import android.util.Log
import com.google.gson.Gson
import hash.application.dataType.Favorites
import hash.application.dataType.Recipe
import java.io.File
import java.util.ArrayList

/**
 * Created by gouji on 3/25/2018.
 */
//use Singleton by object
object FavoriteManager {
    private val fav: Favorites = Favorites()

    fun initFromFile(dir: File){
        val dataFile = FileManager(dir,"favorites.dat")
        if (!dataFile.checkFile()) {
            dataFile.proofFile()
        }
        val rawString = dataFile.readFile()
        val favorites: Favorites = Gson().fromJson(rawString, Favorites::class.java)
        if (favorites.favorites == null) {//catch parsing failure, favorites could be null
            Log.e("log_FavoriteManager", "favorites is null")
            return
        }
        for(i in favorites.favorites){
            addRecipe(i)
        }
    }

    fun addRecipe(recipe: Recipe): Boolean {
        return fav.favorites.add(recipe)
    }

    fun removeRecipebyID(id: String): Boolean {
        for (i in fav.favorites) {
            if (i.id == id) {
                return fav.favorites.remove(i)
            }
        }
        return false
    }

    fun findRecipeByName(name: String): Boolean {
        for (i in fav.favorites) {
            if (i.name == name) {
                return true
            }
        }
        return false
    }

    fun findRecipeById(id: String): Boolean {
        for (i in fav.favorites) {
            if (i.id == id) {
                return true
            }
        }
        return false
    }

    fun getNameList(): ArrayList<String> {
        val ret: ArrayList<String> = ArrayList()
        for (i in fav.favorites) {
            ret.add(i.name)
        }
        return ret
    }
}