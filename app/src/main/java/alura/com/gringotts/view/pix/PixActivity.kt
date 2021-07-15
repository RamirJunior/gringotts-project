package alura.com.gringotts.view.pix

import alura.com.gringotts.R
import alura.com.gringotts.databinding.ActivityPixBinding
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class PixActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.pixNavHostFragment) as NavHostFragment
        val graph = navHost.navController.navInflater.inflate(R.navigation.pix_navigation)
        val goToOnboarding = navHost.arguments?.getBoolean("pixOnboardingWasExecuted")
        Log.d("Onboarding Pix Valor : ", goToOnboarding.toString())
        graph.startDestination =
            if (goToOnboarding == true) R.id.onboardingPixFragment2 else R.id.pixFragment2

        //val navController = navHost.navController
        //navController.graph = graph
        navHost.navController.graph = graph
    }
    
}
