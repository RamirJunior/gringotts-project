package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentOnboardingBinding
import alura.com.gringotts.presentation.LoginViewModel
import alura.com.gringotts.presentation.OnboardingViewModel
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
    private val onBordingViewModel by viewModel<OnboardingViewModel>()

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

        fun onBoardingFinished() { //Salva que a onboard foi executada para não ter que repetir
            onBordingViewModel.onBoardingFinished()
        }

        criarConta.setOnClickListener { //errado
            //fun onClick(v : View) {
            //   val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(registerUrl))
            //    this.startActivity(openUrl)
            // }
        }

        jaTenhoConta.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            onBoardingFinished()
        }

        return view
    }
}