package alura.com.gringotts.view


import alura.com.gringotts.databinding.Funcionalidade1Binding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PageOneFragment : Fragment() {
    private var _binding: Funcionalidade1Binding? = null
    //private var binding: Funcionalidade1Binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Funcionalidade1Binding.inflate(inflater, container, false)
        return _binding!!.root
    }

}


