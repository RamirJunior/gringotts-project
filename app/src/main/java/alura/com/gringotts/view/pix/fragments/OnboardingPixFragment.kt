package alura.com.gringotts.view.pix.fragments

import alura.com.gringotts.databinding.FragmentOnboardingPixBinding
import alura.com.gringotts.presentation.pix.OnboardingPixViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnboardingPixFragment : Fragment() {

    private val homeServicesViewModel by viewModel<OnboardingPixViewModel>()
    private var _binding: FragmentOnboardingPixBinding? = null
    private val binding: FragmentOnboardingPixBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingPixBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeServicesViewModel.onboardingPixFinished()
    }

}
