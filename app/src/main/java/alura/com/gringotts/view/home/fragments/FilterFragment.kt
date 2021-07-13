package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.Filter
import alura.com.gringotts.databinding.FilterFragmentBinding
import alura.com.gringotts.view.home.adapters.FilterListAdapter
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class FilterFragment : Fragment() {
    private var _binding: FilterFragmentBinding? = null
    private val binding: FilterFragmentBinding get() = _binding!!
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btApplyfilters.setOnClickListener {
            var resultLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == FilterFragment.RESULT_OK) {

                        val data: Intent? = result.data

                    }
                }

            fun openSomeActivityForResult() {
                val intent = Intent(activity, FilterFragment::class.java)
                resultLauncher.launch(intent)
            }

        }
        binding.filterRecyclerView.adapter =
            FilterListAdapter(transactionsDaysFilter())
    }

    private fun transactionsDaysFilter(): List<Filter> {
        return listOf(
            Filter(getString(R.string.tres_dias)),
            Filter(getString(R.string.sete_dias)),
            Filter(getString(R.string.trinta_dias)),
            Filter(getString(R.string.sessenta_dias)),
            Filter(getString(R.string.centoevinte_dias))
        )
    }

    companion object {
        const val RESULT_OK = 1
        const val REQUEST_OK = 0
    }
}
