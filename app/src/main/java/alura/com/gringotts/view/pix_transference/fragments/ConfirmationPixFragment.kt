package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentConfirmationPixBinding
import alura.com.gringotts.presentation.pix_transference.ConfirmationPixViewModel
import alura.com.gringotts.presentation.pix_transference.InsertOptionalDescriptionPixViewModel
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
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

        pixSharedViewModel.name.observe(viewLifecycleOwner, {
            binding.textviewUsername.text = it
            pixSharedViewModel.saveName(it)
        })

        pixSharedViewModel.email.observe(viewLifecycleOwner, {
            binding.textviewEmail.text = it
            pixSharedViewModel.saveEmail(it)
        })

        pixSharedViewModel.description.observe(viewLifecycleOwner, {
            binding.textviewDescription.text = it
            if (it != null) {
                pixSharedViewModel.saveMessage(it)
            }
        })

        pixSharedViewModel.institution.observe(viewLifecycleOwner, {
            binding.textviewBankName.text = it
            pixSharedViewModel.saveInstitution(it)
        })

        pixSharedViewModel.value.observe(viewLifecycleOwner, {
            binding.textviewValue.text = it
            binding.textviewTotalValue.text = it
            pixSharedViewModel.savePixValue(it.toDouble())
        })

        pixSharedViewModel.date.observe(viewLifecycleOwner, {
            binding.textviewDate.text = it
            pixSharedViewModel.saveDate(it)
        })

        pixSharedViewModel.loading.observe(viewLifecycleOwner, {
            binding.loadingConfirmation.isVisible = it
        })

        binding.toolbarPixConfirmation.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.continueConfirmation.setOnClickListener {
        val direction = ConfirmationPixFragmentDirections.actionConfirmationPixFragmentToPixFinishedFragment(arguments.pix)
            findNavController().navigate(direction)
            pixSharedViewModel.confirmPix()
        }

        binding.textviewDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Agende a Transfêrencia")
                .setSelection(pixConfirmationViewModel.pixDateInMillis.value)
                .build()
            datePicker.addOnPositiveButtonClickListener {
                pixConfirmationViewModel.positiveDataPicker(it)
            }
            datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
        }

        pixConfirmationViewModel.pixDate.observe(viewLifecycleOwner) {
            pixSharedViewModel.saveDate(it)
            pixSharedViewModel.validationPix()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
