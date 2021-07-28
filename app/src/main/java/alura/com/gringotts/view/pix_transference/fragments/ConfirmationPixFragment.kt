package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentConfirmationPixBinding
import alura.com.gringotts.presentation.pix_transference.ConfirmationPixViewModel
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfirmationPixFragment : Fragment() {

    private var _binding: FragmentConfirmationPixBinding? = null
    private val binding: FragmentConfirmationPixBinding get() = _binding!!
    private val pixSharedViewModel by sharedViewModel<PixSharedViewModel>()
    private val pixConfirmationViewModel by viewModel<ConfirmationPixViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmationPixBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pixSharedViewModel.saveDate("28/07/2021")
        pixSharedViewModel.validationPix()

        pixSharedViewModel.loading.observe(viewLifecycleOwner, {
            updateLayout(it)
        })

        var datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Agende a Transfêrencia")
                .setSelection(pixConfirmationViewModel.pixDateInMillis.value)
                .build()
        binding.toolbarPixConfirmation.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.continueConfirmation.setOnClickListener {
            findNavController().navigate(R.id.action_confirmationPixFragment_to_pixFinishedFragment)
        }
        binding.textviewDatePicker.setOnClickListener{
             datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Agende a Transfêrencia")
                    .setSelection(pixConfirmationViewModel.pixDateInMillis.value)
                    .build()
//            datePicker.show(requireActivity().supportFragmentManager,datePicker.toString())
        }

        datePicker.addOnPositiveButtonClickListener {
            pixConfirmationViewModel.positiveDataPicker(it)
        }
    }

    private fun updateLayout(loadingStatus : Boolean) {
        if(loadingStatus) {
            binding.textviewEmail.text = pixSharedViewModel.getPix().receiverEmail
            binding.textviewDescription.text = pixSharedViewModel.getPix().message
            binding.textviewValue.text = pixSharedViewModel.getPix().pixValue.toString()
            binding.textviewDate.text = pixSharedViewModel.getPix().date
            binding.textviewBankName.text = pixSharedViewModel.getPix().institution
            binding.textviewUsername.text = pixSharedViewModel.getPix().receiverName
            binding.textviewTotalValue.text = pixSharedViewModel.getPix().pixValue.toString()
        }else{
            //chama carregando
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
