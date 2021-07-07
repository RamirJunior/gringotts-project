package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentHomeBinding
import alura.com.gringotts.presentation.HomeViewModel
import alura.com.gringotts.view.pageview.PageOneFragment
import alura.com.gringotts.view.pageview.PageThreeFragment
import alura.com.gringotts.view.pageview.PageTwoFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.benefits.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = BenefitsListAdapter(it)
            binding.recyclerView.addItemDecoration(DotsIndicatorDecoration())
            PagerSnapHelper().attachToRecyclerView(binding.recyclerView)
        }

        homeViewModel.balance.observe(viewLifecycleOwner) {
            binding.balanceValue.text = it
        }

        homeViewModel.receivable.observe(viewLifecycleOwner) {
            binding.receivableValue.text = it
        }

        binding.hideBalance.setOnClickListener {
            homeViewModel.hideBalanceButtonClicked()
        }

        homeViewModel.userName.observe(viewLifecycleOwner) {
            binding.userName.text = it
        }

        homeViewModel.visibilityId.observe(viewLifecycleOwner) {
            binding.hideBalance.setImageResource(it)
        }


        val fragmentList = arrayListOf<Fragment>(
            PageOneFragment(),
            PageTwoFragment(),
            PageThreeFragment()
        )

        val adapter = TabLayoutAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.pagerFuncionalidades.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(
            binding.tabLayoutFuncionalidades,
            binding.pagerFuncionalidades,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position + 1) {
                    1 -> {
                        tab.text = getString(R.string.principais)
                        adapter.to(0)
                    }
                    2 -> {
                        tab.text = getString(R.string.produtosInvestimentos)
                        adapter.to(1)
                    }
                    3 -> {
                        tab.text = getString(R.string.servicos)
                        adapter.to(2)
                    }
                }
            })
        tabLayoutMediator.attach()
    }
}