package alura.com.gringotts.view.filter.fragment

import alura.com.gringotts.R
import alura.com.gringotts.presentation.filter.model.Filter
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

    private var localPostition : Int = 1
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
        localPostition=getPositionFromValue(
            requireActivity().intent.extras!!.getInt("range")
        )
        filterList[localPostition].isChecked = true
        binding.filterRecyclerView.adapter =
            FilterListAdapter(filterList, this@FilterFragment, localPostition)

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
        return when (localPostition) {
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

    private fun getPositionFromValue(newRange: Int): Int{
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
}
