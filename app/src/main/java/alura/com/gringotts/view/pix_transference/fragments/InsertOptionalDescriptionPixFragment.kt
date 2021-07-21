package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.databinding.FragmentInsertOptionalDescriptionPixBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

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

        binding.editDescription.isCounterEnabled = true
        binding.editDescription.counterMaxLength = 20

        binding.toolbarPixDescription.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.pixDescriptionContinue.setOnClickListener {

        }

    }

}
