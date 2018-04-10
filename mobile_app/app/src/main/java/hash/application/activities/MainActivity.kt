package hash.application.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import hash.application.R
import hash.application.fragments.Fragment3
import hash.application.fragments.*
import hash.application.helpers.BottomNavigationViewHelper
import hash.application.managers.FavoriteManager
import hash.application.managers.IngredientManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//initalize layout
        FavoriteManager.initFromFile(this.filesDir)//initalize Favoritemanager from file
        IngredientManager.initData()//initalize IngredientManager, will be changed in future to init from file

        BottomNavigationViewHelper.disableShiftMode(navigation)//change outlook of buttom navigation bar

        supportFragmentManager.beginTransaction().replace(R.id.framelayout,Fragment1()).commit()//initalize the application with 1st fragment
        navigation.selectedItemId = R.id.menu_item1

        //click on buttons on navigation bar, go to different fragments
        navigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            if(navigation.selectedItemId != item.itemId) {
                when (item.itemId) {
                    R.id.menu_item1 -> selectedFragment = Fragment1()
                    R.id.menu_item2 -> selectedFragment = Fragment2()
                    R.id.menu_item3 -> selectedFragment = Fragment3()
                    R.id.menu_item4 -> selectedFragment = Fragment4()
                    R.id.menu_item5 -> selectedFragment = Fragment5()
                }
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.framelayout, selectedFragment)//switch to chosen fragment
                transaction.commit()
            }
            true
        }
    }
}
