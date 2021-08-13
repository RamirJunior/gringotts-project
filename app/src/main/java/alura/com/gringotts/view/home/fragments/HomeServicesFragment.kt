package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.FuncionalityItem
import alura.com.gringotts.databinding.HomeServicesLayoutBinding
import alura.com.gringotts.presentation.home.HomeServicesViewModel
import alura.com.gringotts.view.home.adapters.FuncionalityListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeServicesFragment : Fragment() {
    private val homeServicesViewModel by viewModel<HomeServicesViewModel>()
    private var _binding: HomeServicesLayoutBinding? = null
    private val binding: HomeServicesLayoutBinding get() = _binding!!
    private var goToOnboardingPix = true

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
        requireArguments().getInt(POSITION_KEY)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter =
            FuncionalityListAdapter(
                getItensByPosition(requireArguments().getInt(POSITION_KEY)),
                object : FuncionalityListAdapter.OnSelectOnClickListener {
                    override fun onSelect(position: Int) {
                        when (getItensByPosition(requireArguments().getInt(POSITION_KEY))[position].title) {
                            PIX_NAME -> {
                                val direction =
                                    HomeFragmentDirections.actionHomeFragmentToPixActivity(
                                        goToOnboardingPix
                                    )
                                findNavController().navigate(
                                    direction
                                )
                            }
                            else -> {
                                Toast.makeText(context, NOT_IMPLEMENTED_SCREEN, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                    }
                })
    }

    override fun onResume() {
        super.onResume()
        homeServicesViewModel.onViewCreated()
        homeServicesViewModel.goToOnboardingPix.observe(viewLifecycleOwner) {
            goToOnboardingPix = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            FuncionalityItem(getString(R.string.PostosShell), R.drawable.ic_postos_shell),
            FuncionalityItem(getString(R.string.Ofertas), R.drawable.ic_ofertas),
            FuncionalityItem(getString(R.string.Shopping), R.drawable.ic_carrinho_de_compras),
            FuncionalityItem(getString(R.string.LocaisDeSaque), R.drawable.ic_saque),
            FuncionalityItem(getString(R.string.IndiqueGanhe), R.drawable.ic_presente),
            FuncionalityItem(getString(R.string.PagarComQrCode), R.drawable.ic_pagar_com_qr_code)
        )
    }

    private fun functionalitiesPageTwo(): List<FuncionalityItem> {
        return listOf(
            FuncionalityItem(getString(R.string.AplicarDinheiro), R.drawable.ic_investir),
            FuncionalityItem(getString(R.string.Investimentos), R.drawable.ic_meus_investimentos),
            FuncionalityItem(getString(R.string.Seguros), R.drawable.ic_seguros),
            FuncionalityItem(getString(R.string.AprendaInvestir), R.drawable.ic_aprenda_a_investir),
        )
    }

    private fun functionalitiesPageOne(): List<FuncionalityItem> {
        return listOf(
            FuncionalityItem(getString(R.string.Transferencias), R.drawable.ic_transferecnia),
            FuncionalityItem(getString(R.string.Cartoes), R.drawable.ic_cartoes),
            FuncionalityItem(getString(R.string.PagarContas), R.drawable.ic_codigo_barras_vector),
            FuncionalityItem(getString(R.string.Recargas), R.drawable.ic_celular_recarga),
            FuncionalityItem(getString(R.string.Depositar), R.drawable.ic_adicionar_dinheiro),
            FuncionalityItem(getString(R.string.Pix), R.drawable.logo_pix_final)
        )
    }

    companion object {
        private const val PIX_NAME = "Pix"
        private const val POSITION_KEY = "position"
        private const val NOT_IMPLEMENTED_SCREEN = "Tela não implementada"
    }
}
