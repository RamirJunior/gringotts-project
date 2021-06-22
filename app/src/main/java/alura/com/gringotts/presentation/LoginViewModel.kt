package alura.com.gringotts.presentation

import alura.com.gringotts.data.LoginRepository
import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern


//Onde vamos realizar as verificaçoes
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {


    val loginResult: Boolean = false // váriavel pra saber se o login foi válido

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password) //Logando no nosso Banco de dados ou algo do tipo

        if (result) { //Se verdadeiro

        } else { //Senão

        }
    }

    //Verifica se o CPF é válido
    private fun isCPFValid(cpf: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val CPF_PATTERN = "^(?=.[0-9])(?=.{11,})"

        pattern = Pattern.compile(CPF_PATTERN)
        matcher = pattern.matcher(cpf)

        return matcher.matches()
    }

    //Verifica se o CNPJ é válido
    private fun isCNPJValid(cnpj: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val CNPJ_PATTERN = "^(?=.[0-9])(?=.{14,})"

        pattern = Pattern.compile(CNPJ_PATTERN)
        matcher = pattern.matcher(cnpj)

        return matcher.matches()
    }

    // Método para Validar a senha utilizando Pattern e Matcher
    private fun isPasswordValid(password: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher

        val PASSWORD_PATTERN = "^(?=.[0-9])(?=.[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*])(?=.{6,})"
        //(?=.*[a-z]) a senha tem que ter uma letra minuscula
        //(?=.[0-9]) a senha tem que ter um número de 0 à 9
        //(?=.*[A-Z]) a senha tem que conter uma letra maiuscula
        //(?=.*[!@#$%^&*]) a senha tem que ter algum caractere especial
        //(?=.{6,}) a senha tem quer ter 6 caracteres ou mais

        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)



        return matcher.matches()
    }
}