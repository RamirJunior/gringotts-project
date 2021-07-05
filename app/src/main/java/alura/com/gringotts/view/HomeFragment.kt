package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.data.model.Benefits
import alura.com.gringotts.databinding.FragmentHomeBinding
import alura.com.gringotts.databinding.FragmentLoginBinding
import alura.com.gringotts.presentation.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        _binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val benefitsList = listOf(Benefits("#FFEB3B", "https://assets.pagseguro.com.br/ps-website-assets/v14.44.0/img/_pswn/generic/large/pagamento-contas-cartao-credito.png", "title", "message", "link"),
            Benefits("#4CAF50", "https://assets.pagseguro.com.br/ps-website-assets/v14.44.0/img/_pswn/generic/large/pagamento-contas-cartao-credito.png", "title1", "message1", "link1"))
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = BenefitsListAdapter(benefitsList)
        //homeViewModel.getHomeData()
    }

    val adapter = ViewPagerAdapter(this)
    binding.pagerFuncionalidades.adapter = adapter
    val tabLayoutMediator = TabLayoutMediator(binding.tabLayoutFuncionalidades,
        binding.pagerFuncionalidades,
        TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
            when(position){
                0 -> {
                    tab.text = "@strings/principais"
                }
                1 -> {
                    tab.text = "@strings/produtosInvestimentos"
                }
                2 -> {
                    tab.text = "@strings/servicos"
                }
            }
        })
    tabLayoutMediator.attach()

}