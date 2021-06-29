package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentOnboardingBinding
import alura.com.gringotts.presentation.OnboardingViewModel
import alura.com.gringotts.presentation.SplashViewModel
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var criarConta: Button
    private lateinit var jaTenhoConta: TextView
    private val onboardingViewModel by viewModel<OnboardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentOnboardingBinding.inflate(layoutInflater) //Setando o nosso Layout de login
        val view = binding.root //Setando o nosso Layout de login


        criarConta = binding.buttonCriarConta
        jaTenhoConta = binding.textViewJaTenhoCadastro


        criarConta.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment2_to_criarContaFragment2)
            onboardingViewModel.onBoardingFinished()
        }

        jaTenhoConta.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment2_to_login_navigation)
            onboardingViewModel.onBoardingFinished()
        }

        return view
    }
}