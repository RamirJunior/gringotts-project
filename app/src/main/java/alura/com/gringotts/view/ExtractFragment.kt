package alura.com.gringotts.view

import alura.com.gringotts.databinding.FragmentHomeBinding
import alura.com.gringotts.presentation.ExtractViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExtractFragment : Fragment() {
    private val extractViewModel by viewModel<ExtractViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractViewModel.getCalendar()
        Log.e("aaaa", "aaaaaa")
    }
}