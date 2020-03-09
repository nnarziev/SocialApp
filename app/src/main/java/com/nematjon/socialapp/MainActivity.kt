package com.nematjon.socialapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        /*val bottomNavigationView = findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.home -> Toast.makeText(
                    this, "Home", Toast.LENGTH_SHORT
                ).show()
                R.id.action_likes -> Toast.makeText(
                    this, "Likes", Toast.LENGTH_SHORT
                ).show()
                R.id.action_dm -> Toast.makeText(
                    this, "DMs", Toast.LENGTH_SHORT
                ).show()
                R.id.action_profile -> Toast.makeText(
                    this, "Profile", Toast.LENGTH_SHORT
                ).show()
            }
            true
        }*/
    }
}
