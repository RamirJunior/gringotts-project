package alura.com.gringotts.view.initial.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentOnboardingBinding
import alura.com.gringotts.presentation.initial.OnboardingViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val onBordingViewModel by viewModel<OnboardingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCriarConta.setOnClickListener {
            Toast.makeText(context, getString(R.string.TelaNaoImplementada), Toast.LENGTH_LONG)
                .show()
        }

        binding.textViewJaTenhoCadastro.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment2_to_login_navigation)
            onBordingViewModel.onBoardingFinished()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
