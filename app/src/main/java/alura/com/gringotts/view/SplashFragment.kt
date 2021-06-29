package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.presentation.LoginViewModel
import alura.com.gringotts.presentation.SplashViewModel
import android.content.Context
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val splashViewModel by viewModel<SplashViewModel>()
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            if (splashViewModel.getFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_login_navigation) //leva para tela de login se ja tiver sharedpref
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onboarding_navigation) // leva para tela de onboarding se for a primeira vez que o aplicativo esta sendo executado
            }
        }, 3000)

        return view
    }



}