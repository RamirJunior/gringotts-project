package alura.com.gringotts.view.initial.fragments

import alura.com.gringotts.R
import alura.com.gringotts.presentation.initial.SplashViewModel
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private val splashViewModel by viewModel<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            splashViewModel.goToLogin.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.action_splashFragment_to_login_navigation)
            }
            splashViewModel.goToOnboarding.observe(viewLifecycleOwner) {
                findNavController().navigate(R.id.action_splashFragment_to_onboarding_navigation)
            }
        }, 3000)
    }

}
