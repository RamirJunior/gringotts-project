package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.databinding.ActivityHomeBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.nav_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}
