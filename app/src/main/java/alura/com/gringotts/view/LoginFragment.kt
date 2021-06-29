package alura.com.gringotts.view

import alura.com.gringotts.databinding.FragmentLoginBinding
import alura.com.gringotts.presentation.LoginViewModel
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var buttonLogin: Button
    private lateinit var loading: ProgressBar
    private lateinit var register: Button

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var remember: SwitchCompat
    private lateinit var forgotPassword: TextView
    private val loginViewModel by viewModel<LoginViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater) //Setando o nosso Layout de login
        val view = binding.root //Setando o nosso Layout de login

        username = binding.loginUsername // pegando o nome no layout login
        password = binding.loginPassword // pegando o password no layout de login
        buttonLogin = binding.loginLogin //  botao de login
        loading = binding.loading // barra de login
        register = binding.loginRegister // barra de login
        remember = binding.loginRemember //Lembrar Usuário
        forgotPassword = binding.loginForgot // Esqueceu Senha

        username.setText(loginViewModel.getUsername())
        password.setText(loginViewModel.getPassword())
        remember.isChecked = loginViewModel.rememberSwitch.value == true

        username.addTextChangedListener {
            loginViewModel.setUsername(it.toString())
        }
        password.addTextChangedListener {
            loginViewModel.setPassword(it.toString())
        }

        remember.setOnCheckedChangeListener { buttonView, isChecked ->
            loginViewModel.switchChanged(isChecked)
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner, {
            if(it){
                //passa para proxima tela
                //findNavController().navigate(R.id.action_loginFragment2_to_home_navigation)
            }
            else{
                val alertDialogBuilder = AlertDialog.Builder(activity)
                alertDialogBuilder.setMessage(loginViewModel.getError())
                alertDialogBuilder.setNegativeButton("Ok",
                    DialogInterface.OnClickListener { dialog, id ->

                    })
                alertDialogBuilder.show()
            }
        })

        buttonLogin.setOnClickListener {
            loginViewModel.onLoginButtonClicked()
        }

        loginViewModel.loading.observe(viewLifecycleOwner, {
            if (it) {
                loading.visibility = View.VISIBLE
                buttonLogin.isClickable = false
                register.isClickable = false
            } else {
                loading.visibility = View.GONE
                buttonLogin.isClickable = true
                register.isClickable = true
            }
        })

        register.setOnClickListener {
            //findNavController().navigate(R.id.action_loginFragment2_to_criarContaFragment)
        }

        forgotPassword.setOnClickListener {
            //findNavController().navigate(R.id.action_loginFragment2_to_esqueciMinhaSenhaFragment)
        }

        return view
    }
}