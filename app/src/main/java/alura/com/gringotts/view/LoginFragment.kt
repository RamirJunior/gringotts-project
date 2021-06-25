package alura.com.gringotts.view

import alura.com.gringotts.databinding.FragmentLoginBinding
import alura.com.gringotts.presentation.LoginViewModel
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.ViewModelProvider
import forgotUrl
import registerUrl

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var buttonLogin: Button
    private lateinit var loading: ProgressBar
    private lateinit var register: Button

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var remember: SwitchCompat
    private lateinit var forgotPassword: TextView


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


        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java) //Iniciando o view model
        //loginViewModel.init(SharedPreferencesIMPL(viewLifecycleOwner))

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
            Log.e("b", isChecked.toString())
            loginViewModel.switchClicked(isChecked)
        }


        buttonLogin.setOnClickListener { // Quando o usuário clicar para logar
            //loading.visibility = View.VISIBLE // Falando pro loading aparecer na tela
            loginViewModel.login() //Verifica se existe no sistema o usuário em questão
        }

        register.setOnClickListener {
            @Override
            fun onClickRegister() {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(registerUrl)
            }
        }

        forgotPassword.setOnClickListener {
            @Override
            fun onClickRegister() {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(forgotUrl)
            }
        }

        return view
    }
}