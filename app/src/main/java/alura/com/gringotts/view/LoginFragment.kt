package alura.com.gringotts.view

import alura.com.gringotts.databinding.FragmentLoginBinding
import alura.com.gringotts.databinding.FragmentOnboardingBinding
import alura.com.gringotts.presentation.LoginViewModel
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {


    private val loginViewModel by viewModel<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginUsername.setText(loginViewModel.getUsername())
        binding.loginPassword.setText(loginViewModel.getPassword())
        binding.loginRemember.isChecked = loginViewModel.rememberSwitch.value == true

        binding.loginUsername.addTextChangedListener {
            loginViewModel.setUsername(it.toString())
        }
        binding.loginPassword.addTextChangedListener {
            loginViewModel.setPassword(it.toString())
        }

        binding.loginRemember.setOnCheckedChangeListener { buttonView, isChecked ->
            loginViewModel.switchChanged(isChecked)
        }

        loginViewModel.loginError.observe(viewLifecycleOwner, {

            val alertDialogBuilder = AlertDialog.Builder(activity)
            alertDialogBuilder.setMessage(it)
            alertDialogBuilder.setNegativeButton("Ok",
                DialogInterface.OnClickListener { dialog, id ->

                })
            alertDialogBuilder.show()
        })
        loginViewModel.loginSuccess.observe(viewLifecycleOwner,{
            Toast.makeText(context, "Login! proxima tela não implementada", Toast.LENGTH_LONG)
        })
        binding.loginLogin.setOnClickListener {
            loginViewModel.onLoginButtonClicked()
        }

        loginViewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                binding.loading.visibility = View.VISIBLE
                binding.loginLogin.isClickable = false
                binding.loginRegister.isClickable = false
            } else {
                binding.loading.visibility = View.GONE
                binding.loginLogin.isClickable = true
                binding.loginRegister.isClickable = true
            }
        })

        binding.loginRegister.setOnClickListener {
            Toast.makeText(context, "Tela não implementada", Toast.LENGTH_LONG)
        }

        binding.loginForgot.setOnClickListener {
            Toast.makeText(context, "Tela não implementada", Toast.LENGTH_LONG)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
