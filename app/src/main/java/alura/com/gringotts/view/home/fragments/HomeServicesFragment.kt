package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.FuncionalityItem
import alura.com.gringotts.databinding.HomeServicesLayoutBinding
import alura.com.gringotts.presentation.home.HomeServicesViewModel
import alura.com.gringotts.view.adapters.FuncionalityListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeServicesFragment : Fragment() {

    private val homeServicesViewModel by viewModel<HomeServicesViewModel>()
    private var _binding: HomeServicesLayoutBinding? = null
    private val binding: HomeServicesLayoutBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeServicesLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeServicesViewModel.goToPix.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeFragment_to_pix_navigation)
        }
        homeServicesViewModel.goToPixOnboarding.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_homeFragment_to_pix_navigation)
        }

        requireArguments().getInt("position")
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter =
            FuncionalityListAdapter(
                getItensByPosition(requireArguments().getInt("position")),
                object : FuncionalityListAdapter.OnSelectOnClickListener {
                    override fun onSelect(position: Int) {
                        if (getItensByPosition(requireArguments().getInt("position")).get(position).title == "Pix") {
                            homeServicesViewModel.pixClicked()
                        }
                    }
                })
    }

    private fun getItensByPosition(position: Int): List<FuncionalityItem> {
        return when (position) {
            0 -> functionalitiesPageOne()
            1 -> functionalitiesPageTwo()
            else -> functionalitiesPageThree()
        }
    }

    private fun functionalitiesPageThree(): List<FuncionalityItem> {
        return listOf(
            FuncionalityItem("Postos Shell", R.drawable.ic_postos_shell),
            FuncionalityItem("Ofertas", R.drawable.ic_ofertas),
            FuncionalityItem("Shopping", R.drawable.ic_carrinho_de_compras),
            FuncionalityItem("Locais de saque", R.drawable.ic_saque),
            FuncionalityItem("Indique e Ganhe", R.drawable.ic_presente),
            FuncionalityItem("Pagar com QR Code", R.drawable.ic_pagar_com_qr_code)
        )
    }

    private fun functionalitiesPageTwo(): List<FuncionalityItem> {
        return listOf(
            FuncionalityItem("Aplicar dinheiro", R.drawable.ic_investir),
            FuncionalityItem("Investimentos", R.drawable.ic_meus_investimentos),
            FuncionalityItem("Seguros", R.drawable.ic_seguros),
            FuncionalityItem("Aprenda a investir", R.drawable.ic_aprenda_a_investir),
        )
    }

    private fun functionalitiesPageOne(): List<FuncionalityItem> {
        return listOf(
            FuncionalityItem("Transferências", R.drawable.ic_transferecnia),
            FuncionalityItem("Cartões", R.drawable.ic_cartoes),
            FuncionalityItem("Pagar contas", R.drawable.ic_codigo_barras_vector),
            FuncionalityItem("Recargas", R.drawable.ic_celular_recarga),
            FuncionalityItem("Depositar", R.drawable.ic_adicionar_dinheiro),
            FuncionalityItem("Pix", R.drawable.logo_pix_final)
        )
    }
}
