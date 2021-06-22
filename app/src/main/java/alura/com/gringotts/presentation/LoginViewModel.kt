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

    // Método para Validar a senha
    private fun isPasswordValid(password: String): Boolean {
    }
}