package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentPixTypeChoiceBinding
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PixTypeChoiceFragment : Fragment() {

    private var _binding: FragmentPixTypeChoiceBinding? = null
    private val binding: FragmentPixTypeChoiceBinding get() = _binding!!
    private val pixSharedViewModel by sharedViewModel<PixSharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixTypeChoiceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarPixChoice.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.cardViewEmail.setOnClickListener {
            findNavController().navigate(R.id.action_pixTypeChoiceFragment_to_insertEmailPixFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
