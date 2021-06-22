package alura.com.gringotts.view

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import alura.com.gringotts.presentation.LoginViewModel
import alura.com.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater) //Setando o nosso Layout de login
        setContentView(binding.root) //Setando o nosso Layout de login

        val username = binding.username // pegando o nome no layout login
        val password = binding.password // pegando o password no layout de login
        val login = binding.login //  botao de login
        val loading = binding.loading // barra de login

        //View model do resultado do login
        loginViewModel.loginResult.observe(this@LoginActivity, Observer {

        })

    }
}