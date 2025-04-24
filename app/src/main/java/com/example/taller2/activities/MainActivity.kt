    package com.example.taller2.activities

    import android.os.Bundle
    import androidx.appcompat.app.ActionBarDrawerToggle
    import androidx.appcompat.app.AppCompatActivity
    import androidx.appcompat.widget.Toolbar
    import androidx.drawerlayout.widget.DrawerLayout
    import androidx.navigation.NavController
    import androidx.navigation.fragment.NavHostFragment
    import androidx.navigation.ui.AppBarConfiguration
    import androidx.navigation.ui.setupActionBarWithNavController
    import androidx.navigation.ui.setupWithNavController
    import com.example.taller2.R
    import com.google.android.material.navigation.NavigationView


    class MainActivity : AppCompatActivity() {

        private lateinit var navController: NavController
        private lateinit var appBarConfiguration: AppBarConfiguration
        private lateinit var drawerLayout: DrawerLayout
        private lateinit var toolbar: Toolbar
        private lateinit var drawerToggle: ActionBarDrawerToggle


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)


            toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            drawerLayout = findViewById(R.id.drawer_layout)
            val navView = findViewById<NavigationView>(R.id.nav_view)

            drawerToggle = ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer
            )
            drawerLayout.addDrawerListener(drawerToggle)
            drawerToggle.syncState()

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.inicioFragment,
                    R.id.productosFragment,
                    R.id.carritoFragment,
                    R.id.perfilFragment,
                    R.id.CategoriasFragment,
                    R.id.editarPerfilFragment
                ),
                drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

        }
        override fun onSupportNavigateUp(): Boolean {
            return navController.navigateUp() || super.onSupportNavigateUp()
        }
    }
