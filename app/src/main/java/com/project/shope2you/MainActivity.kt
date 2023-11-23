package com.project.shope2you

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.shope2you.databinding.ActivityMainBinding

class MainActivity :  AppCompatActivity(R.layout.activity_main) {

    private lateinit var appBarConfig: AppBarConfiguration
    //rivate lateinit var Binding:ActivityMainBindingp



    var hasCompletedWelcome: Boolean
        get() = getPreferences(MODE_PRIVATE).getBoolean("hasCompletedWelcome", false)
        set(value) = getPreferences(MODE_PRIVATE).edit()
            .putBoolean("hasCompletedWelcome", value)
            .apply()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.navigation)

        if (hasCompletedWelcome){
            graph.setStartDestination(R.id.home2)
        }else {
            graph.setStartDestination(R.id.login)
        }

        val navController2 = navHostFragment.navController
        navController2.setGraph(graph, intent.extras)

        val bottom=findViewById<BottomNavigationView>(R.id.bottomnav)

        //val navController = findNavController()
        appBarConfig = AppBarConfiguration(navController2.graph)
        setupActionBarWithNavController(navController2, appBarConfig)
        bottom.setupWithNavController(navController2)

        navController2.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.login) {
                supportActionBar?.hide()
                bottom.visibility=View.GONE


            }
            else if (destination.id == R.id.signup2) {
            supportActionBar?.hide()
                bottom.visibility=View.GONE
        }
            else if (destination.id == R.id.home2) {
                //supportActionBar?.hide()
                bottom.visibility=View.VISIBLE
            }
            else {
                supportActionBar?.show()
                bottom.visibility=View.VISIBLE
            }
        }

        /*if (!hasCompletedWelcome) {
            //navController.navigate(R.id.welcomeFragment)
           // navigateToLogin()
            //resetStartDestination()
        }*/
    }

    private fun navigateToLogin() {
        //findNavController().navigate(MainFragmentDirections.actionMainFragmentToWelcomeFragment())
        findNavController().navigate(R.id.login)
    }

    fun resetStartDestination() {
        val navController = findNavController()
        val graph = navController.graph
        graph.setStartDestination(R.id.home2)

        navController.graph = graph
    }

    fun restart() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun findNavController(): NavController {
        return findNavController(R.id.nav_host_fragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}