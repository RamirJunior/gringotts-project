package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentAccountStatementBinding
import alura.com.gringotts.presentation.home.AccountStatementViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountStatementFragment : Fragment() {
    private val accountStatementViewModel by viewModel<AccountStatementViewModel>()
    private var _binding: FragmentAccountStatementBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountStatementBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountStatementViewModel.loading.observe(viewLifecycleOwner){

        }
        binding.transactionsFilter.setOnClickListener {
            findNavController().navigate(R.id.action_accountStatementFragment_to_filterFragment)
        }

    }
}