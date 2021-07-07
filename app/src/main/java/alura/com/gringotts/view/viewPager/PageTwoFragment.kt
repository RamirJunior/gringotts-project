package alura.com.gringotts.view.viewPager

import alura.com.gringotts.databinding.Funcionality2Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PageTwoFragment : Fragment() {

    private var _binding: Funcionality2Binding? = null
    private val binding: Funcionality2Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Funcionality2Binding.inflate(inflater, container, false)
        return binding.root
    }

}
