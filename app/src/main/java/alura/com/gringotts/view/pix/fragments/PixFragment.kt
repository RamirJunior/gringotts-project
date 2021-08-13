package alura.com.gringotts.view.pix.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentPixBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class PixFragment : Fragment() {

    private var _binding: FragmentPixBinding? = null
    private val binding: FragmentPixBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPixBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarPix.setNavigationOnClickListener {
            activity?.finish()
        }

        binding.cardView2.setOnClickListener {
            findNavController().navigate(R.id.action_pixFragment2_to_pixTypeChoiceFragment)
        }

        binding.cardView7.setOnClickListener {
            Toast.makeText(context, "Tela não implementada", Toast.LENGTH_LONG).show()
        }

        with(binding) {
            setOnFunctionNoDeveloped(listOf(cardView, cardView3, cardView4, cardView6, cardView7))
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
