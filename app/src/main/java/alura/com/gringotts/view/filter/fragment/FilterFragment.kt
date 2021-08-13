package alura.com.gringotts.view.filter.fragment

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FilterFragmentBinding
import alura.com.gringotts.presentation.filter.model.Filter
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

    private var selectedFilterPosition: Int = 1
    private val filterList by lazy {
        listOf(
            Filter(getString(R.string.tres_dias)),
            Filter(getString(R.string.sete_dias)),
            Filter(getString(R.string.trinta_dias)),
            Filter(getString(R.string.sessenta_dias)),
            Filter(getString(R.string.centoevinte_dias))
        )
    }

    override fun callbackPosition(position: Int) {
        selectedFilterPosition = position
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
        selectedFilterPosition = getPositionFromValue(
            requireActivity().intent.extras!!.getInt(RANGE_KEY)
        )
        filterList[selectedFilterPosition].isChecked = true
        binding.filterRecyclerView.adapter =
            FilterListAdapter(filterList, this@FilterFragment)

        binding.btApplyfilters.setOnClickListener {
            requireActivity().setResult(
                SUCCESSFUL_CODE,
                Intent().putExtra(FILTER_KEY, returnValueOfPosition())
            )
            requireActivity().finish()
        }
        binding.toolbarFilter.setNavigationOnClickListener {
            requireActivity().setResult(
                FAILURE_CODE
            )
            requireActivity().finish()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun returnValueOfPosition(): Int {
        return when (selectedFilterPosition) {
            0 -> {
                3
            }
            1 -> {
                7
            }
            2 -> {
                30
            }
            3 -> {
                60
            }
            else -> {
                120
            }
        }
    }

    private fun getPositionFromValue(newRange: Int): Int {
        return when (newRange) {
            3 -> {
                0
            }
            7 -> {
                1
            }
            30 -> {
                2
            }
            60 -> {
                3
            }
            else -> {
                4
            }
        }
    }

    companion object {
        private const val RANGE_KEY: String = "range_key"
        private const val FILTER_KEY: String = "key_filter"
        private const val SUCCESSFUL_CODE: Int = 2
        private const val FAILURE_CODE: Int = 3
    }

}
