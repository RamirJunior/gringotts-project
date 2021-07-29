package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentPixFinishedBinding
import alura.com.gringotts.presentation.pix_transference.ConfirmationPixViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PixFinishedFragment : Fragment() {

    private var _binding: FragmentPixFinishedBinding? = null
    private val binding: FragmentPixFinishedBinding get() = _binding!!
    private val arguments by navArgs<PixFinishedFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixFinishedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateLayout()

        binding.imageButtonGoToHome.setOnClickListener {
            activity?.finish()
        }

        binding.imageButtonOtherTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_pixFinishedFragment_to_pixFragment2)
        }

    }

    fun updateLayout() {
        binding.pixFinishedDate.text = arguments.pix.date
        binding.pixTransferCurrency.text = arguments.pix.pixValue.toString()
        binding.pixFinishedReceiverName.text = arguments.pix.name
        binding.pixFinishedEmailValue.text = arguments.pix.receiverEmail
        binding.pixFinishedInstitution.text = arguments.pix.institution
    }

}
