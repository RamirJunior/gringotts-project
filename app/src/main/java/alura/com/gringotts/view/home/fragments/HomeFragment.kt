package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentHomeBinding
import alura.com.gringotts.presentation.home.HomeViewModel
import alura.com.gringotts.view.home.adapters.BenefitsListAdapter
import alura.com.gringotts.view.home.adapters.HomeServicesAdapter
import alura.com.gringotts.view.home.decoration.DotsIndicatorDecoration
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val onPixReceiverBroadcast = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            homeViewModel.getHomeData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val localBroadcast = LocalBroadcastManager.getInstance(requireContext())
        localBroadcast.registerReceiver(onPixReceiverBroadcast, IntentFilter("PIX_RECEIVED"))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.recyclerView.addItemDecoration(DotsIndicatorDecoration())
        PagerSnapHelper().attachToRecyclerView(binding.recyclerView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.benefits.observe(viewLifecycleOwner) {
            binding.recyclerView.adapter = BenefitsListAdapter(it)
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

        homeViewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingHome.isVisible = it
        }

        homeViewModel.hideBalanceAndReceivable.observe(viewLifecycleOwner) {
            if (it) {
                binding.balanceValue.inputType = InputType.TYPE_CLASS_TEXT
                binding.receivableValue.inputType = InputType.TYPE_CLASS_TEXT
                binding.hideBalance.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                binding.balanceValue.inputType =
                    InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                binding.receivableValue.inputType =
                    InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                binding.hideBalance.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
        }

        homeViewModel.apiError.observe(viewLifecycleOwner, {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        getString(R.string.ok)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        val localBroadcast = LocalBroadcastManager.getInstance(requireContext())
        localBroadcast.unregisterReceiver(onPixReceiverBroadcast)
    }

}
