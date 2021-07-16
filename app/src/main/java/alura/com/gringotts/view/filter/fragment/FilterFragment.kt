package alura.com.gringotts.view.filter.fragment

import alura.com.gringotts.R
import alura.com.gringotts.data.models.filter.Filter
import alura.com.gringotts.databinding.FilterFragmentBinding
import alura.com.gringotts.view.filter.adapter.FilterListAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FilterFragment : Fragment(), FilterListAdapter.SelectItemFilterListener {
    private var _binding: FilterFragmentBinding? = null
    private val binding: FilterFragmentBinding get() = _binding!!

    private var localPostition = 1
    private val filterList by lazy {
        listOf(
            Filter(getString(R.string.tres_dias)),
            Filter(getString(R.string.sete_dias)),
            Filter(getString(R.string.trinta_dias)),
            Filter(getString(R.string.sessenta_dias)),
            Filter(getString(R.string.centoevinte_dias))
        )
    }

    override fun returnPosition(position: Int) {
        localPostition = position

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filterRecyclerView.adapter =
            FilterListAdapter(filterList, this@FilterFragment)

        binding.btApplyfilters.setOnClickListener {
            requireActivity().setResult(
                2,
                Intent().putExtra("key_filter", returnValueOfPosition())
            )
            requireActivity().finish()
        }
        binding.btVoltaParaExtrato.setOnClickListener {
            requireActivity().setResult(
                2,
                Intent().putExtra("key_filter", 3)
            )
            requireActivity().finish()
        }

    }

    private fun returnValueOfPosition(): Int {
        if (localPostition == 0) {
            return 3
        } else if (localPostition == 1) {
            return 7
        } else if (localPostition == 2) {
            return 30
        } else if (localPostition == 3) {
            return 60
        } else {
            return 120
        }
    }

    companion object {
        const val RESULT_OK = 1
    }
}
