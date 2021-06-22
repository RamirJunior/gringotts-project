package alura.com.gringotts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

import alura.com.gringotts.R
import alura.com.gringotts.data.LoginRepository

//Onde vamos realizar as verificaçoes
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    fun login(username: String, password: String) {
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {


    }

    // Método para validar E-mail
    private fun isEmailValid(email: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val EMAIL_PATTERN = ".+@.+\.[a-z]+"

        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)

        return matcher.matches()
    }

    // Método para verificar CPF e CNPJ
    private fun isCPFValid(cpf: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val CPF_PATTERN = "..-\.[0-9]"

        pattern = Pattern.compile(CPF_PATTERN)
        matcher = pattern.matcher(cpf)

        return matcher.matches()
    }

    private fun isCNPJValid(cnpj: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val CNPJ_PATTERN = "../-\.[0-9]"

        pattern = Pattern.compile(CNPJ_PATTERN)
        matcher = pattern.matcher(cnpj)

        return matcher.matches()
    }

    // Método para Validar a senha
    private fun isPasswordValid(password: String): Boolean {

    }
}