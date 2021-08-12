package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentConfirmationPixBinding
import alura.com.gringotts.presentation.pix_transference.ConfirmationPixViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ConfirmationPixFragment : Fragment() {

    private var _binding: FragmentConfirmationPixBinding? = null
    private val binding: FragmentConfirmationPixBinding get() = _binding!!
    private val arguments by navArgs<ConfirmationPixFragmentArgs>()
    private val confirmationPixViewModel: ConfirmationPixViewModel by viewModel {
        parametersOf(arguments.pix)
    }

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

        confirmationPixViewModel.pixUpdate.observe(viewLifecycleOwner, {
            binding.textviewUsername.text = it.name
            binding.textviewEmail.text = it.receiverEmail
            binding.textviewDescription.text = it.message
            binding.textviewBankName.text = it.institution
            binding.textviewValue.text = it.pixValue.toString()
            binding.textviewTotalValue.text = it.pixValue.toString()
            binding.textviewDate.text = it.date
        })

        confirmationPixViewModel.loading.observe(viewLifecycleOwner, {
            binding.loadingConfirmation.isVisible = it
        })

        binding.toolbarPixConfirmation.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.continueConfirmation.setOnClickListener {
            confirmationPixViewModel.confirmPix()
        }

        binding.textViewCancelar.setOnClickListener {
            activity?.finish()
        }

        confirmationPixViewModel.goToPixFinishedFragment.observe(viewLifecycleOwner) {
            val direction =
                ConfirmationPixFragmentDirections.actionConfirmationPixFragmentToPixFinishedFragment(
                    arguments.pix
                )
            findNavController().navigate(direction)
        }

        binding.textviewDatePicker.setOnClickListener {
            val constraintsBuilder =
                CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now())
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraintsBuilder.build())
                .setTitleText(getString(R.string.pix_date_picker_title))
                .setSelection(confirmationPixViewModel.pixDateInMillis.value)
                .build()
            datePicker.addOnPositiveButtonClickListener {
                confirmationPixViewModel.onClickPositiveDatePicker(it)
            }
            datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
        }

        confirmationPixViewModel.pixDate.observe(viewLifecycleOwner) {
            arguments.pix.date = it
            confirmationPixViewModel.validationPix()
        }

        confirmationPixViewModel.pixError.observe(viewLifecycleOwner) {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        getString(R.string.ok)
                    ) { _, _ -> }
                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
