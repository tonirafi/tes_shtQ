package com.tes.tesshtq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tes.tesshtq.home.PurchasedActivity
import com.tes.tesshtq.home.SearchActivity

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setNav()
    }


    private fun setNav() {

        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.navigation_home)
                }
                R.id.nav_feed -> {
//                    navController.navigate(R.id.navigation_feed)
                }
                R.id.nav_cart -> {
//                    navController.navigate(R.id.navigation_cart)
                }
                R.id.nav_profile -> {
                    var inten= Intent(this, PurchasedActivity::class.java)
                    startActivity(inten)

                }
            }
            true
        }


    }


}