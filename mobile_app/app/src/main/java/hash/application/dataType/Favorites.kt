package hash.application.dataType

import java.util.ArrayList

/**
 * Created by gouji on 2/26/2018.
 */
class Favorites {

    data class DishList(
            val dishes: ArrayList<Dish>
    )

    data class Dish(val name: String,val hashName: String)
}