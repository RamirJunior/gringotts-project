package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentInsertOptionalDescriptionPixBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class InsertOptionalDescriptionPixFragment : Fragment() {

    private var _binding: FragmentInsertOptionalDescriptionPixBinding? = null
    private val binding: FragmentInsertOptionalDescriptionPixBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertOptionalDescriptionPixBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btBackPixChoice.setOnClickListener{
            activity?.onBackPressed()
        }

        binding.pixDescriptionContinue.setOnClickListener{

        }

    }

}
