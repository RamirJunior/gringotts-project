package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentPixValueBinding
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import alura.com.gringotts.presentation.pix_transference.PixValueViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PixValueFragment : Fragment() {

    private var _binding: FragmentPixValueBinding? = null
    private val binding: FragmentPixValueBinding get() = _binding!!
    private val pixSharedViewModel by sharedViewModel<PixSharedViewModel>()
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

        pixValueViewModel.balance.observe(viewLifecycleOwner) {
            binding.balanceValue.text = it
        }

        binding.editValue.addTextChangedListener {
            pixValueViewModel.pixValueToFloat = it.toString().toDouble()
        }

        pixValueViewModel.goToConfirmationPixFragment.observe(viewLifecycleOwner) {
            pixSharedViewModel.savePixValue(it)
            findNavController().navigate(R.id.action_pixValueFragment_to_confirmationPixFragment)
        }

    }
}
