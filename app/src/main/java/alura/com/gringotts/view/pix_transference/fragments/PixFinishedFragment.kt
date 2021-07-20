package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.databinding.FragmentPixFinishedBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PixFinishedFragment : Fragment() {

    private var _binding: FragmentPixFinishedBinding? = null
    private val binding: FragmentPixFinishedBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixFinishedBinding.inflate(layoutInflater)
        return binding.root
    }

}
