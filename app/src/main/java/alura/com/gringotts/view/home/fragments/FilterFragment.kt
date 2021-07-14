package alura.com.gringotts.view.home.fragments

import alura.com.gringotts.R
import alura.com.gringotts.data.models.home.Filter
import alura.com.gringotts.databinding.FilterFragmentBinding
import alura.com.gringotts.view.home.adapters.FilterListAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FilterFragment : Fragment(), FilterListAdapter.selectItemFilterListener {
    private var _binding: FilterFragmentBinding? = null
    private val binding: FilterFragmentBinding get() = _binding!!
//    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        // Handle the returned Uri
//    }

    private var localPostition = 1
    private val filterList: List<Filter> = listOf(
        Filter(getString(R.string.tres_dias)),
        Filter(getString(R.string.sete_dias)),
        Filter(getString(R.string.trinta_dias)),
        Filter(getString(R.string.sessenta_dias)),
        Filter(getString(R.string.centoevinte_dias))
    )
//    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            // There are no request codes
//            val data: Intent? = result.data
//           // doSomeOperations()
//        }
//    }

    override fun returnPosition(position: Int) {
        localPostition = position

    }

//    private fun doSomeOperations() {
//        TODO("Not yet implemented")
//    }

//    fun openSomeActivityForResult() {
//        val intent = Intent(this, FilterActivity::class.java)
//        resultLauncher.launch(intent)
//    }

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
        binding.filterRecyclerView.adapter =
            FilterListAdapter(filterList, this@FilterFragment)

        binding.btApplyfilters.setOnClickListener {
            requireActivity().setResult(
                2,
                Intent().putExtra("key_filter", filterList[localPostition].toString())
            )
            requireActivity().finish()
        }
    }

//    private fun transactionsDaysFilter(): List<Filter> {
//        return listOf(
//            Filter(getString(R.string.tres_dias)),
//            Filter(getString(R.string.sete_dias)),
//            Filter(getString(R.string.trinta_dias)),
//            Filter(getString(R.string.sessenta_dias)),
//            Filter(getString(R.string.centoevinte_dias))
//        )
//    }

    companion object {
        const val RESULT_OK = 1
    }
}
