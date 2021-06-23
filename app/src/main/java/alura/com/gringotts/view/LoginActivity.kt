package alura.com.gringotts.view

import alura.com.gringotts.data.SharedPreferencesProvider
import alura.com.gringotts.databinding.ActivityLoginBinding
import alura.com.gringotts.presentation.LoginViewModel
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import forgotUrl
import registerUrl

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var buttonLogin: Button
    private lateinit var loading: ProgressBar
    private lateinit var register: Button

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var remember: SwitchCompat
    private lateinit var forgotPassword: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater) //Setando o nosso Layout de login
        setContentView(binding.root) //Setando o nosso Layout de login
        username = binding.loginUsername // pegando o nome no layout login
        password = binding.loginPassword // pegando o password no layout de login
        buttonLogin = binding.loginLogin //  botao de login
        loading = binding.loading // barra de login
        register = binding.loginRegister // barra de login
        remember = binding.loginRemember //Lembrar Usuário
        forgotPassword = binding.loginForgout // Esqueceu Senha

        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java) //Iniciando o view model
        loginViewModel.init(SharedPreferencesProvider(this))

        username.setText(loginViewModel.currentUsername.value.toString())
        password.setText(loginViewModel.currentPassword.value.toString())
        remember.isChecked = loginViewModel.rememberSwitch.value == true

        username.addTextChangedListener {
            loginViewModel.setUsername(it.toString())
        }
        password.addTextChangedListener {
            loginViewModel.setPassword(it.toString())
        }
        remember.setOnClickListener {
            loginViewModel.switchClicked(remember.isChecked)
        }

        loginViewModel.enableButtonLogin.observe(this, {
            buttonLogin.isEnabled = it
        })

        buttonLogin.setOnClickListener { // Quando o usuário clicar para logar
            loading.visibility = View.VISIBLE // Falando pro loading aparecer na tela
            loginViewModel.login() //Verifica se existe no sistema o usuário em questão
        }

        register.setOnClickListener {
            //abre a pagina de cadastro do PagBank
            @Override
            fun onClickRegister() {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(registerUrl)
            }
        }

        forgotPassword.setOnClickListener {
            //abre a pagina de cadastro do PagBank
            @Override
            fun onClickRegister() {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(forgotUrl)
            }
        }

        //Gerenciador de Conexão
        //val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //val activeNetwork: NetworkInfo? =connectionManager.activeNetworkInfo
        //Valor falso corresponde a ausência de conexão
        //val isConnected:Boolean = activeNetwork?.isConnectedOrConnecting == false

        //Checagem e Layout
        //if (isConnected) {

        //}
    }


    override fun onResume() {
        super.onResume()
        username.setText(loginViewModel.currentUsername.value.toString())
        password.setText(loginViewModel.currentPassword.value.toString())
        remember.isChecked = loginViewModel.rememberSwitch.value == true
    }
}
