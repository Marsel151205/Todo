package com.marsel.todo.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.marsel.todo.R
import com.marsel.todo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The project uses a Single Activity
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupBottomNavigation()
        setupStartDestination()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigation.setupWithNavController(navController)

        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onBoardingFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                    binding.fab.visibility = View.GONE
                }

                R.id.homeFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                    binding.fab.visibility = View.VISIBLE
                    binding.tvTitleScreen.text = "Главная"
                }
                R.id.calendarFragment -> {
                    binding.tvTitleScreen.text = "Календарь"
                }
            }
        }
    }

    private fun setupStartDestination() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        Log.d("isLogin", "${viewModel.getIsFirstLogin()}")
        when (viewModel.getIsFirstLogin()) {
            false -> navGraph.setStartDestination(R.id.onBoardingFragment)
            true -> navGraph.setStartDestination(R.id.homeFragment)
        }
        navController.graph = navGraph
    }
}