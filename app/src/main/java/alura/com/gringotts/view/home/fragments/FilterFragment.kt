package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.data.models.home.Filter
import alura.com.gringotts.databinding.FragmentAccountStatementBinding
import alura.com.gringotts.view.home.adapters.FilterListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FilterFragment : Fragment() {
    private var _binding: FragmentAccountStatementBinding? = null
    private val binding: FragmentAccountStatementBinding get() = _binding!!

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
        binding.recyclerViewTransactions.adapter =
            FilterListAdapter(transactionsDaysFilter())
    }

    private fun transactionsDaysFilter(): List<Filter> {
        return listOf(
            Filter("Ùltimos 3 dias"),
            Filter("Ùltimos 7 dias"),
            Filter("Ùltimos 30 dias"),
            Filter("Ùltimos 60 dias"),
            Filter("Ùltimos 120 dias")
        )
    }
}