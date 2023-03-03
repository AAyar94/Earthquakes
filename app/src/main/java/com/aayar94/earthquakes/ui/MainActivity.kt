package com.aayar94.earthquakes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.aayar94.earthquakes.R
import com.aayar94.earthquakes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostController) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.lastEarthquakesFragment,
                R.id.riskMapFragment
            )
        )

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}