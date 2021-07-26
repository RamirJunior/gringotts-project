package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentInsertOptionalDescriptionPixBinding
import alura.com.gringotts.presentation.pix_transference.InsertOptionalDescriptionPixViewModel
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertOptionalDescriptionPixFragment : Fragment() {

    private var _binding: FragmentInsertOptionalDescriptionPixBinding? = null
    private val binding: FragmentInsertOptionalDescriptionPixBinding get() = _binding!!
    private val insertOptionalDescriptionPixViewModel by viewModel<InsertOptionalDescriptionPixViewModel>()
    private val pixSharedViewModel by sharedViewModel<PixSharedViewModel>()

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
            if (it != null) {
                pixSharedViewModel.saveMessage(it)
            }
            findNavController().navigate(R.id.action_insertOptionalDescriptionPixFragment_to_pixValueFragment)
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
