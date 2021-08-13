package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.databinding.FragmentAccountStatementBinding
import alura.com.gringotts.presentation.home.AccountStatementViewModel
import alura.com.gringotts.view.filter.FilterActivity
import alura.com.gringotts.view.home.adapters.TransactionListAdapter
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
        val adapter = TransactionListAdapter(listOf())
        binding.recyclerViewTransactions.adapter = adapter
        accountStatementViewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingAccountStatement.isVisible = it
        }
        accountStatementViewModel.accountStatementError.observe(viewLifecycleOwner) {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        "Ok"
                    ) { _, _ -> }
                    .show()
            }
        }
        accountStatementViewModel.currentTransactionsList.observe(viewLifecycleOwner) {
            adapter.setAdapterList(it)
        }
        accountStatementViewModel.showEmptyListPlaceHolder.observe(viewLifecycleOwner) {
            binding.recyclerViewTransactions.isVisible = !it
            binding.emptyListContainer.isVisible = it
        }
        binding.chipInput.setOnClickListener {
            accountStatementViewModel.setOnlyEntries()
        }
        binding.chipAll.setOnClickListener {
            accountStatementViewModel.setAllTransactions()
        }
        binding.chipOutput.setOnClickListener {
            accountStatementViewModel.setWithdraw()
        }

        binding.toolbar.setOnMenuItemClickListener {
            startActivityForResult(
                Intent(requireActivity(), FilterActivity::class.java)
                    .putExtra(RANGE_KEY, accountStatementViewModel.currentRange),
                REQUEST_CODE
            )
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_SUCCESS_CODE) {
            accountStatementViewModel.changeRange(data!!.getIntExtra(FILTER_KEY, DEFAULT_RANGE))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val RANGE_KEY: String = "range_key"
        private const val REQUEST_CODE = 2
        private const val RESULT_SUCCESS_CODE = 2
        private const val FILTER_KEY: String = "key_filter"
        private const val DEFAULT_RANGE: Int = 7
    }
}
