package alura.com.gringotts.view.viewPager

import alura.com.gringotts.databinding.Funcionality3Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PageThreeFragment : Fragment() {

    private var _binding: Funcionality3Binding? = null
    private val binding: Funcionality3Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Funcionality3Binding.inflate(inflater, container, false)
        return binding.root
    }

}
