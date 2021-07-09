package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentHomeBinding
import alura.com.gringotts.presentation.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

        homeViewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingHome.isVisible = it
        }

        homeViewModel.apiError.observe(viewLifecycleOwner, {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        "Ok"
                    ) { _, _ -> }
                    .show()
            }
        })

        val adapter = HomeServicesAdapter(
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.pagerFuncionalidades.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(
            binding.tabLayoutFuncionalidades,
            binding.pagerFuncionalidades
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.principais)
                }
                1 -> {
                    tab.text = getString(R.string.produtosInvestimentos)
                }
                2 -> {
                    tab.text = getString(R.string.servicos)
                }
            }
        }
        tabLayoutMediator.attach()
    }
}
