package alura.com.gringotts.view

import alura.com.gringotts.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class OnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        fun onBoardingFinished(){ //Salva que a onboard foi executada para não ter que repetir
            val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("Finished", true)
            editor.apply()
        }

        view.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            onBoardingFinished()
        }

        return view
    }
}