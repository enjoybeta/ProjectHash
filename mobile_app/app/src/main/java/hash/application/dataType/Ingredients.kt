package hash.application.dataType

import java.util.ArrayList

/**
 * Created by gouji on 2/26/2018.
 */
class Ingredients {

    data class ItemList(
        val fruits: ArrayList<Item>,
        val vegetables: ArrayList<Item>,
        val protein: ArrayList<Item>,
        val dairy: ArrayList<Item>,
        val grains: ArrayList<Item>,
        val oils: ArrayList<Item>
    )

    data class Item(val name: String,val quantity: String)
}