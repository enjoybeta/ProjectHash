package hash.application.dataType

import android.os.Bundle
import java.util.ArrayList

/**
 * Created by gouji on 3/19/2018.
 */
class Recipe(
        val name: String,
        val id: String,
        val totaltime: Int,
        val imageURLs: String,
        val numberofserving: Int,
        val flavor: String,
        val instructionurl: String,
        val ingredientLines: ArrayList<String>) {

    fun getRecipeBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("name", name ?: "")
        bundle.putString("id", id ?: "")
        bundle.putInt("totaltime", totaltime ?: 0)
        bundle.putString("imageURLs", imageURLs ?: "https://vignette.wikia.nocookie.net/janethevirgin/images/4/42/Image-not-available_1.jpg/revision/latest?cb=20150721102313")
        bundle.putInt("numberofserving", numberofserving ?: 0)
        bundle.putString("flavor", flavor ?: "")
        bundle.putString("instructionurl", instructionurl ?: "")
        bundle.putStringArrayList("ingredientLines", ingredientLines ?: ArrayList())
        return bundle
    }
}