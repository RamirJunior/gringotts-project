package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentPixFinishedBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PixFinishedFragment : Fragment() {

    private var _binding: FragmentPixFinishedBinding? = null
    private val binding: FragmentPixFinishedBinding get() = _binding!!

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
        binding.pixFinishedDate.text = pixSharedViewModel.date.value
        binding.pixTransferCurrency.text = pixSharedViewModel.value.value
        binding.pixFinishedReceiverName.text = pixSharedViewModel.name.value
        binding.pixFinishedEmailValue.text = pixSharedViewModel.email.value
        binding.pixFinishedInstitution.text = pixSharedViewModel.institution.value
    }

}
