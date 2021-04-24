package com.mike.samples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.mike.samples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val navHostFragment: NavHostFragment? get() {
        return supportFragmentManager.findFragmentById(android.R.id.content) as? NavHostFragment
    }

    private val navController: NavController? get() {
        return navHostFragment?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHost = MainNavHostFragment()

        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, navHost)
            .setPrimaryNavigationFragment(navHost)
            .commitNow()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment?.navController?.navigateUp() == true || super.onSupportNavigateUp()
    }

    class MainNavHostFragment: NavHostFragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.activity_main, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val viewBinding = ActivityMainBinding.bind(view)

            super.onViewCreated(viewBinding.content, savedInstanceState)

            val activity = requireActivity() as AppCompatActivity
            activity.setSupportActionBar(viewBinding.toolbar)

            val appbarConfig = AppBarConfiguration.Builder(
                R.id.indexFragment, R.id.secondFragment).build()

            NavigationUI.setupActionBarWithNavController(activity, navController, appbarConfig)
        }

        override fun onCreateNavController(navController: NavController) {
            super.onCreateNavController(navController)

            navController.setGraph(R.navigation.nav_graph)
        }
    }
}