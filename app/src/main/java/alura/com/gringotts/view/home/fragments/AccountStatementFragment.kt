package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.databinding.FragmentAccountStatementBinding
import alura.com.gringotts.presentation.home.AccountStatementViewModel
import alura.com.gringotts.view.home.FilterActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        accountStatementViewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingAccountStatement.isVisible = it
        }
        accountStatementViewModel.accountStatementError.observe(viewLifecycleOwner){
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        "Ok"
                    ) { _, _ -> }
                    .show()
            }
        }
        binding.transactionsFilter.setOnClickListener {
            // findNavController().navigate(R.id.action_accountStatementFragment_to_filterFragment)
            startActivityForResult(
                Intent(requireActivity(), FilterActivity::class.java), REQUEST_CODE
            )
        }
        binding.chipInput.setOnClickListener {
            accountStatementViewModel.setOnlyEntries()
        }
        binding.chipAll.setOnClickListener {
            accountStatementViewModel.setAllTransactions()
        }
//        override fun initInstance(){
//            binding.transactionsFilter.setOnClickListener {
//                override fun onClick(view: View) {
//                    val intent = Intent(this, FilterFragment::class.java)
//                    startActivityForResult(intent, REQUEST_CODE)
//                }
//            }
//
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            //  data!!.getStringExtra("key_filter")
        }
    }

    companion object {
        const val REQUEST_CODE = 2
    }
}
