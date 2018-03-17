package com.hash.application

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import hash.application.fragments.Fragment3
import hash.application.R
import hash.application.fragments.*
import hash.application.helpers.BottomNavigationViewHelper
import kotlinx.android.synthetic.main.activity_main.*

val serverAddress: String = "172.26.10.12"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BottomNavigationViewHelper.disableShiftMode(navigation)

        supportFragmentManager.beginTransaction().replace(R.id.framelayout,Fragment1()).commit()
        navigation.selectedItemId = R.id.menu_item1

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
                transaction.replace(R.id.framelayout, selectedFragment)
                transaction.commit()
            }
            true
        }
    }
}
