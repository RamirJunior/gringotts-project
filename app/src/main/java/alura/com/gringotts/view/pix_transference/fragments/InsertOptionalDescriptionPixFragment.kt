package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.databinding.FragmentInsertOptionalDescriptionPixBinding
import alura.com.gringotts.presentation.pix_transference.InsertOptionalDescriptionPixViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class InsertOptionalDescriptionPixFragment : Fragment() {

    private var _binding: FragmentInsertOptionalDescriptionPixBinding? = null
    private val binding: FragmentInsertOptionalDescriptionPixBinding get() = _binding!!
    private val arguments by navArgs<InsertOptionalDescriptionPixFragmentArgs>()
    private val insertOptionalDescriptionPixViewModel: InsertOptionalDescriptionPixViewModel by viewModel {
        parametersOf(arguments.pix)
    }

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

        binding.toolbarPixDescription.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.editTextDescription.addTextChangedListener {
            insertOptionalDescriptionPixViewModel.currentDescription = it.toString()
        }

        insertOptionalDescriptionPixViewModel.goToPixValueFragment.observe(viewLifecycleOwner) {
            val direction =
                InsertOptionalDescriptionPixFragmentDirections.actionInsertOptionalDescriptionPixFragmentToPixValueFragment(
                    arguments.pix
                )
            findNavController().navigate(direction)
        }

        binding.pixDescriptionContinue.setOnClickListener {
            insertOptionalDescriptionPixViewModel.onInsertDescriptionButtonClicked()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
