package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentPixValueBinding
import alura.com.gringotts.presentation.pix_transference.PixValueViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PixValueFragment : Fragment() {

    private var _binding: FragmentPixValueBinding? = null
    private val binding: FragmentPixValueBinding get() = _binding!!
    private val pixValueViewModel by viewModel<PixValueViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixValueBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarPixValue.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.pixValueContinue.setOnClickListener {
            pixValueViewModel.onValueButtonClicked()
        }

        pixValueViewModel.hideValueString.observe(viewLifecycleOwner) {
            binding.balanceValue.text = it
        }

        binding.editValue.addTextChangedListener {
            if (it != null) {
                pixValueViewModel.pixValue = it.toString()
            }
        }

        pixValueViewModel.goToConfirmationPixFragment.observe(viewLifecycleOwner) {
            pixSharedViewModel.savePixValue(it.toDouble())
            findNavController().navigate(R.id.action_pixValueFragment_to_confirmationPixFragment)
        }

        binding.hideBalancePix.setOnClickListener {
            pixValueViewModel.hideBalanceButtonClickedPix()
        }

        pixValueViewModel.hideButtonText.observe(viewLifecycleOwner) {
            binding.hideBalancePix.text = it
        }

        pixValueViewModel.loading.observe(viewLifecycleOwner) {
            binding.loading.isVisible = it
        }

        pixValueViewModel.invalidValueError.observe(viewLifecycleOwner) {
            binding.editValue.error = it
        }

        pixValueViewModel.apiError.observe(viewLifecycleOwner, {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        "Ok"
                    ) { _, _ -> }
                    .show()
            }
        })

    }
}
