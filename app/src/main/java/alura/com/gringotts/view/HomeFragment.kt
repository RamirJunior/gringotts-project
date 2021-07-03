package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.data.model.Benefits
import alura.com.gringotts.databinding.FragmentHomeBinding
import alura.com.gringotts.databinding.FragmentLoginBinding
import alura.com.gringotts.presentation.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val benefitsList = listOf(Benefits("color", "image", "title", "message", "link"),
            Benefits("color1", "image1", "title1", "message1", "link1"))
        homeViewModel.getHomeData()
    }
}