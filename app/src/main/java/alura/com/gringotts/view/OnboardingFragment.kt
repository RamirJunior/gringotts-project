package alura.com.gringotts.view
import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentOnboardingBinding
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import registerUrl

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var criarConta: Button
    private lateinit var jaTenhoConta: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnboardingBinding.inflate(layoutInflater) //Setando o nosso Layout de login
        val view = binding.root //Setando o nosso Layout de login

        criarConta = binding.buttonCriarConta
        jaTenhoConta = binding.textViewJaTenhoCadastro

        fun onBoardingFinished(){ //Salva que a onboard foi executada para não ter que repetir
            val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("Finished", true)
            editor.apply()
        }

        criarConta.setOnClickListener { //errado
            @Override
            fun onClickRegister() {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(registerUrl)
            }
        }

        jaTenhoConta.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            onBoardingFinished()
        }

        return view
    }
}