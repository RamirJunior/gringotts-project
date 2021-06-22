package alura.com.gringotts.view

import alura.com.gringotts.databinding.ActivityLoginBinding
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import alura.com.gringotts.presentation.LoginViewModel
import android.view.View
import androidx.lifecycle.ViewModelProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater) //Setando o nosso Layout de login
        setContentView(binding.root) //Setando o nosso Layout de login

        val username = binding.loginUsername // pegando o nome no layout login
        val password = binding.loginPassword // pegando o password no layout de login
        val login = binding.loginLogin //  botao de login
        val loading = binding.loading // barra de login
        val register = binding.loginRegister // barra de login
        val remember  = binding.loginRemember //Lembrar Usuário
        val forgotPassword = binding.loginForgout // Esqueceu Senha

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java) //Iniciando o view model

        //View model do resultado do login
        loginViewModel.loginResult.observe(this@LoginActivity, Observer {

        })

        login.setOnClickListener { // Quando o usuário clicar para logar
            loading.visibility = View.VISIBLE // Falando pro loading aparecer na tela
            loginViewModel.login(username.text.toString(), password.text.toString()) //Verifica se existe no sistema o usuário em questão
        }

    }
}