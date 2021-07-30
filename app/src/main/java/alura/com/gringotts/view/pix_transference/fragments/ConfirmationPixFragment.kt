package alura.com.gringotts.view.pix_transference.fragments

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

        confirmationPixViewModel.name.observe(viewLifecycleOwner, {
            binding.textviewUsername.text = it
        })

        confirmationPixViewModel.email.observe(viewLifecycleOwner, {
            binding.textviewEmail.text = it
        })

        confirmationPixViewModel.description.observe(viewLifecycleOwner, {
            binding.textviewDescription.text = it
        })

        confirmationPixViewModel.institution.observe(viewLifecycleOwner, {
            binding.textviewBankName.text = it
        })

        confirmationPixViewModel.value.observe(viewLifecycleOwner, {
            binding.textviewValue.text = it
            binding.textviewTotalValue.text = it
        })

        confirmationPixViewModel.date.observe(viewLifecycleOwner, {
            binding.textviewDate.text = it
        })

        confirmationPixViewModel.loading.observe(viewLifecycleOwner, {
            binding.loadingConfirmation.isVisible = it
        })

        binding.toolbarPixConfirmation.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.continueConfirmation.setOnClickListener {
            val direction =
                ConfirmationPixFragmentDirections.actionConfirmationPixFragmentToPixFinishedFragment(
                    arguments.pix
                )
            findNavController().navigate(direction)
            confirmationPixViewModel.confirmPix()
        }

        binding.textviewDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Agende a Transfêrencia")
                .setSelection(confirmationPixViewModel.pixDateInMillis.value)
                .build()
            datePicker.addOnPositiveButtonClickListener {
                confirmationPixViewModel.positiveDataPicker(it)
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
                        "Ok"
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
