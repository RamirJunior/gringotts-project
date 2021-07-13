package alura.com.gringotts.view.pix.fragments

import alura.com.gringotts.databinding.FragmentPixBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PixFragment : Fragment() {


    private var _binding: FragmentPixBinding? = null
    private val binding: FragmentPixBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixBinding.inflate(inflater, container, false)
        return binding.root
    }

}
