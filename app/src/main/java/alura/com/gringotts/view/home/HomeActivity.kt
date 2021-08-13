package alura.com.gringotts.view.home

import alura.com.gringotts.R
import alura.com.gringotts.databinding.ActivityHomeBinding
import alura.com.gringotts.presentation.home.HomeActivityViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeActivityViewModel by viewModel<HomeActivityViewModel>()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.nav_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            homeActivityViewModel.sendFcmToken(task.result!!)
        })

    }


}
