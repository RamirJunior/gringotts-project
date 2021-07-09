package alura.com.gringotts.view.home

import alura.com.gringotts.R
import alura.com.gringotts.data.model.FuncionalityItem
import alura.com.gringotts.databinding.HomeServicesLayoutBinding
import alura.com.gringotts.view.adapters.FuncionalityListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

class HomeServicesFragment : Fragment() {

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
        requireArguments().getInt("position")
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter =
            FuncionalityListAdapter(getItensByPosition(requireArguments().getInt("position")))
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
            FuncionalityItem("Pagar contas", R.drawable.ic_codigo_barras),
            FuncionalityItem("Recargas", R.drawable.ic_celular_recarga),
            FuncionalityItem("Depositar", R.drawable.ic_adicionar_dinheiro),
            FuncionalityItem("Pix", R.drawable.logo_pix_final)
        )
    }
}
