package alura.com.gringotts.view

import alura.com.gringotts.databinding.Funcionalidade3Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PageThreeFragment : Fragment() {
    private var _binding: Funcionalidade3Binding? = null
    private val binding: Funcionalidade3Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Funcionalidade3Binding.inflate(inflater, container, false)
        return _binding!!.root
    }

}