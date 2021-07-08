package alura.com.gringotts.view

import alura.com.gringotts.R
import alura.com.gringotts.data.model.FuncionalityItem
import alura.com.gringotts.databinding.PageLayoutBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

class PagesFragment : Fragment() {

    private var _binding: PageLayoutBinding? = null
    private val binding: PageLayoutBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PageLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().getInt("position")
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter =
            FuncionalityListAdapter(getItensByPosition(requireArguments().getInt("position")))
    }


    fun getItensByPosition(position: Int): List<FuncionalityItem> {
        if (position == 0) {
            return listOf<FuncionalityItem>(
                FuncionalityItem("Transferências", R.drawable.ic_transferecnia),
                FuncionalityItem("Cartões", R.drawable.ic_cartoes),
                FuncionalityItem("Pagar Contas", R.drawable.ic_codigo_barras),
                FuncionalityItem("Recargas", R.drawable.ic_celular_recarga),
                FuncionalityItem("Adicionar Dinheiro", R.drawable.ic_adicionar_dinheiro),
                FuncionalityItem("Pix", R.drawable.logo_pix_final)
            )
        } else if (position == 1) {
            return listOf<FuncionalityItem>(
                FuncionalityItem("Aplicar meu Dinheiro", R.drawable.ic_investir),
                FuncionalityItem("Meus Investimentos", R.drawable.ic_meus_investimentos),
                FuncionalityItem("Seguros", R.drawable.ic_seguros),
                FuncionalityItem("Aprenda a Investir", R.drawable.ic_aprenda_a_investir),
            )
        } else {
            return listOf<FuncionalityItem>(
                FuncionalityItem("Postos Shell", R.drawable.ic_postos_shell),
                FuncionalityItem("Radar de Ofertas", R.drawable.ic_ofertas),
                FuncionalityItem("Shopping", R.drawable.ic_carrinho_de_compras),
                FuncionalityItem("Onde Sacar Dinheiro", R.drawable.ic_saque),
                FuncionalityItem("Indique e Ganhe", R.drawable.ic_presente),
                FuncionalityItem("Pagar com QR Code", R.drawable.ic_pagar_com_qr_code)
            )
        }
    }
}