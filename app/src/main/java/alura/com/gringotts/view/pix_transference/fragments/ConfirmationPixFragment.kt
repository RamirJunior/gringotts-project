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

        pixSharedViewModel.validationPix()

        pixSharedViewModel.name.observe(viewLifecycleOwner, {
            binding.textviewUsername.text = it
        })

        pixSharedViewModel.email.observe(viewLifecycleOwner, {
            binding.textviewEmail.text = it
        })

        pixSharedViewModel.description.observe(viewLifecycleOwner, {
            binding.textviewDescription.text = it
        })

        pixSharedViewModel.institution.observe(viewLifecycleOwner, {
            binding.textviewBankName.text = it
        })

        pixSharedViewModel.value.observe(viewLifecycleOwner, {
            binding.textviewValue.text = it
            binding.textviewTotalValue.text = it
        })

        pixSharedViewModel.date.observe(viewLifecycleOwner, {
            binding.textviewDate.text = it
        })

//        pixSharedViewModel.loading.observe(viewLifecycleOwner, {
//
//        })

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
             val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Agende a Transfêrencia")
                    .setSelection(pixConfirmationViewModel.pixDateInMillis.value)
                    .build()
            datePicker.show(requireActivity().supportFragmentManager,datePicker.toString())
            datePicker.addOnPositiveButtonClickListener {
                pixConfirmationViewModel.positiveDataPicker(it)
            }
        }

        pixConfirmationViewModel.pixDate.observe(viewLifecycleOwner){
            pixSharedViewModel.saveDate(it)
            binding.textviewDate.text = it
            pixSharedViewModel.validationPix()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
