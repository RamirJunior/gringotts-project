package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.databinding.FragmentPixTypeChoiceBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PixTypeChoiceFragment : Fragment() {

    private var _binding: FragmentPixTypeChoiceBinding? = null
    private val binding: FragmentPixTypeChoiceBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixTypeChoiceBinding.inflate(layoutInflater)
        return binding.root
    }

}
