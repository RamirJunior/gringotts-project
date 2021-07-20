package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentInsertEmailPixBinding
import alura.com.gringotts.presentation.pix_transference.PixTransferenceViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class InsertEmailPixFragment : Fragment() {

    private var _binding: FragmentInsertEmailPixBinding? = null
    private val binding: FragmentInsertEmailPixBinding get() = _binding!!
    private val insertEmailPixViewModel by viewModel<PixTransferenceViewModel>()

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

        binding.toolbarPixInsertEmail.setNavigationOnClickListener{
            activity?.onBackPressed()
        }
        binding.editEmail.addTextChangedListener{
            insertEmailPixViewModel.setEmail(it.toString())
        }
        binding.pixInsertEmailContinue.setOnClickListener{
            insertEmailPixViewModel.onInsertEmailButtonClicked()
        }
        insertEmailPixViewModel.validEmail.observe(viewLifecycleOwner){
            findNavController().navigate(
                R.id.action_insertEmailPixFragment_to_insertOptionalDescriptionPixFragment
            )
        }
        insertEmailPixViewModel.invalidEmail.observe(viewLifecycleOwner){
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        "Ok"
                    ) { _, _ -> }
                    .show()
            }
        }
    }
}
