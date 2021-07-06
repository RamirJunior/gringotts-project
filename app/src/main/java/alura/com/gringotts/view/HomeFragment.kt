package alura.com.gringotts.view

import alura.com.gringotts.databinding.FragmentHomeBinding
import alura.com.gringotts.presentation.HomeViewModel
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

        homeViewModel.receivable.observe(viewLifecycleOwner){
            binding.receivableValue.text = it
        }

        binding.hideBalance.setOnClickListener {
            homeViewModel.hideBalanceButtonClicked()
        }

        homeViewModel.userName.observe(viewLifecycleOwner){
            binding.userName.text = "Olá, " + it
        }

        homeViewModel.visibilityId.observe(viewLifecycleOwner) {
            binding.hideBalance.setImageResource(it)
        }


        val adapter = ViewPagerAdapter(this)
        binding.pagerFuncionalidades.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(binding.tabLayoutFuncionalidades,
            binding.pagerFuncionalidades,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position + 1) {
                    1 -> {
                        tab.text = "@strings/principais"
                    }
                    2 -> {
                        tab.text = "@strings/produtosInvestimentos"
                    }
                    3 -> {
                        tab.text = "@strings/servicos"
                    }
                }
            })
        tabLayoutMediator.attach()
    }
}