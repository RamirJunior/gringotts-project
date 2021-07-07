package alura.com.gringotts.view

import alura.com.gringotts.databinding.Funcionalidade2Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PageTwoFragment : Fragment() {
    private var _binding: Funcionalidade2Binding? = null
    private val binding: Funcionalidade2Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Funcionalidade2Binding.inflate(inflater, container, false)
        return _binding!!.root
    }

}