package org.d3if0113.pokepedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.d3if0113.pokepedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // bottom navigation
        val bottomNav: BottomNavigationView = binding.btnavNavigation
        val navController = findNavController(R.id.fragment)
        val appBarConfig =
            AppBarConfiguration(setOf(R.id.regionNav, R.id.pokedexNav, R.id.favoriteNav))
        setupActionBarWithNavController(navController, appBarConfig)
        bottomNav.setupWithNavController(navController)
    }
}
