package alura.com.gringotts.view.pix_transference.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentPixTypeChoiceBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class PixTypeChoiceFragment : Fragment() {

    private var _binding: FragmentPixTypeChoiceBinding? = null
    private val binding: FragmentPixTypeChoiceBinding get() = _binding!!

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

        with(binding) {
            setOnFunctionNoDeveloped(
                listOf(
                    cardViewPhone,
                    cardViewCpf,
                    cardViewRandomKey,
                    cardViewManualKey
                )
            )
        }

    }

    private fun setOnFunctionNoDeveloped(views: List<CardView>) {
        views.forEach {
            it.setOnClickListener {
                Toast.makeText(
                    context,
                    getString(R.string.FunctionNotImplemented),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
