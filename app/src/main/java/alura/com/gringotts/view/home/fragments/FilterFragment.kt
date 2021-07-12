package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.data.models.home.FuncionalityItem
import alura.com.gringotts.databinding.FragmentAccountStatementBinding
import alura.com.gringotts.view.adapters.FuncionalityListAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

class FilterFragment : Fragment() {
    private var _binding: FragmentAccountStatementBinding? = null
    private val binding: FragmentAccountStatementBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountStatementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().getInt("position")
        binding.recyclerViewTrnasactions.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerViewTrnasactions.adapter =
            FuncionalityListAdapter(getItensByPosition(requireArguments().getInt("position")))
    }

    private fun getItensByPosition(position: Int): List<FuncionalityItem> {
        return when (position) {

        }
    }
}