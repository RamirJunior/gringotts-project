package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentConfirmationPixBinding
import alura.com.gringotts.presentation.pix_transference.ConfirmationPixViewModel
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        var datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Agende a Transfêrencia")
                .setSelection(pixConfirmationViewModel.pixDateInMillis.value)
                .build()

        pixConfirmationViewModel.pixDate.observe(viewLifecycleOwner) {
            binding.textviewDate.text = it
        }

        binding.toolbarPixConfirmation.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.continueConfirmation.setOnClickListener {
            findNavController().navigate(R.id.action_confirmationPixFragment_to_pixFinishedFragment)
            pixSharedViewModel.confirmPix()
        }

        binding.textviewDatePicker.setOnClickListener {
            datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Agende a Transfêrencia")
                    .setSelection(pixConfirmationViewModel.pixDateInMillis.value)
                    .build()
//            datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
            pixSharedViewModel.saveDate("27/07/2021")
            pixSharedViewModel.validationPix()
        }

        datePicker.addOnPositiveButtonClickListener {
            pixConfirmationViewModel.positiveDataPicker(it)
        }

//        binding.textviewValue.text = pixSharedViewModel.getPix().pixValue.toString()
//        binding.textviewTotalValue.text = pixSharedViewModel.getPix().pixValue.toString()
//        binding.textviewUsername.text = pixSharedViewModel.getPix().receiverName
//        binding.textviewEmail.text = pixSharedViewModel.getPix().receiverEmail
//        binding.textviewBankName.text = pixSharedViewModel.getPix().institution
//        binding.textviewDescription.text = pixSharedViewModel.getPix().message
//        binding.textviewDate.text = pixSharedViewModel.getPix().date
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
