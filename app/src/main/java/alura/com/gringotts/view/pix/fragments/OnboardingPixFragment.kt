package alura.com.gringotts.view.pix.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentOnboardingPixBinding
import alura.com.gringotts.presentation.pix.OnboardingPixViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingPixFragment : Fragment() {

    private var _binding: FragmentOnboardingPixBinding? = null
    private val binding: FragmentOnboardingPixBinding get() = _binding!!
    private val onboardingPixViewModel by viewModel<OnboardingPixViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingPixBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pixOnboardingContinue.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingPixFragment2_to_pixFragment2)
            onboardingPixViewModel.onboardingPixFinished()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
