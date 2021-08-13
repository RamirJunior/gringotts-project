package alura.com.gringotts.view.pix

import alura.com.gringotts.R
import alura.com.gringotts.databinding.ActivityPixBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs

class PixActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPixBinding
    private val arguments by navArgs<PixActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPixBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.pixNavHostFragment) as NavHostFragment
        val graph = navHost.navController.navInflater.inflate(R.navigation.pix_navigation)
        val goToOnboarding = arguments.pixOnboardingWasExecuted
        graph.startDestination =
            if (goToOnboarding) R.id.onboardingPixFragment2 else R.id.pixFragment2
        navHost.navController.graph = graph
    }

}
