package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.databinding.FragmentInsertEmailPixBinding
import alura.com.gringotts.presentation.pix_transference.InsertEmailPixViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class InsertEmailPixFragment : Fragment() {

    private var _binding: FragmentInsertEmailPixBinding? = null
    private val binding: FragmentInsertEmailPixBinding get() = _binding!!
    val pix: Pix = Pix("", 0.0, "", "", "", "")
    private val insertEmailPixViewModel: InsertEmailPixViewModel by viewModel { parametersOf(pix) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsertEmailPixBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarPixInsertEmail.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.insertEmailField.addTextChangedListener {
            insertEmailPixViewModel.insertEmail(it.toString())
        }

        insertEmailPixViewModel.isButtonEnable.observe(viewLifecycleOwner) {
            binding.pixInsertEmailContinue.isEnabled = it
        }

        insertEmailPixViewModel.goToInsertDescriptionScreen.observe(viewLifecycleOwner) {
            val direction =
                InsertEmailPixFragmentDirections.actionInsertEmailPixFragmentToInsertOptionalDescriptionPixFragment(
                    pix
                )
            findNavController().navigate(direction)
        }

        binding.pixInsertEmailContinue.setOnClickListener {
            insertEmailPixViewModel.onInsertEmailButtonClicked()
        }

        insertEmailPixViewModel.invalidEmailError.observe(viewLifecycleOwner) {
            binding.editEmail.error = it
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
