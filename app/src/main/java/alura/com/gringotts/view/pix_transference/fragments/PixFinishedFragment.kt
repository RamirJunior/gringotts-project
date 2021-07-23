package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.databinding.FragmentPixFinishedBinding
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PixFinishedFragment : Fragment() {

    private var _binding: FragmentPixFinishedBinding? = null
    private val binding: FragmentPixFinishedBinding get() = _binding!!
    private val pixSharedViewModel by sharedViewModel<PixSharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixFinishedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarPixFinished.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.imageButtonGoToHome.setOnClickListener {
            activity?.finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
