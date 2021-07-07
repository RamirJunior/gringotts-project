package alura.com.gringotts.view


import alura.com.gringotts.databinding.Funcionality1Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PageOneFragment : Fragment() {
    private var _binding: Funcionality1Binding? = null
    private val binding: Funcionality1Binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Funcionality1Binding.inflate(inflater, container, false)
        return _binding!!.root
    }

}


