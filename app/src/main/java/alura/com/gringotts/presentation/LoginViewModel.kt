package alura.com.gringotts.presentation

import alura.com.gringotts.data.LoginRepository
import alura.com.gringotts.data.SharedPreferencesProvider
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern


//Onde vamos realizar as verificaçoes
class LoginViewModel() : ViewModel() {
    private val _enableButtonLogin = MutableLiveData<Boolean>()
    val enableButtonLogin: LiveData<Boolean> = _enableButtonLogin
    private var _currentUsername: String? = ""
    private var usernameIsValid: Boolean = true
    private var _currentPassword :String? = ""
    private var passwordIsValid: Boolean = true
    val loginResult: Boolean = false // váriavel pra saber se o login foi válido
    private val _rememberSwitch = MutableLiveData<Boolean>()
    val rememberSwitch: LiveData<Boolean> = _rememberSwitch
    private lateinit var sharedPeferenceProvider: SharedPreferencesProvider

    fun init(provider: SharedPreferencesProvider) {
        sharedPeferenceProvider=provider
        _rememberSwitch.postValue(sharedPeferenceProvider.getRemeber())
        if(_rememberSwitch.value==true) {
            Log.e("a", "bbb")
            _currentUsername = sharedPeferenceProvider.getUsername()
            _currentPassword = sharedPeferenceProvider.getPassword()
        }
    }

    fun switchClicked(value: Boolean){
        _rememberSwitch.postValue(value)
    }

    fun setUsername(value: String){
        usernameIsValid=true
        _enableButtonLogin.postValue(usernameIsValid && passwordIsValid)
        _currentUsername = value
    }

    fun setPassword(value: String){
        passwordIsValid=true
        _enableButtonLogin.postValue(usernameIsValid && passwordIsValid)
        _currentPassword = value
    }

    fun getUsername(): String{
        if(_currentUsername.equals("")) {
            return ""
        }
        return _currentUsername.toString()
    }
    fun getPassword(): String{
        if(_currentPassword.equals("")) {
            return ""
        }
        return _currentPassword.toString()
    }

    fun login() {
        if(_rememberSwitch.value == true){
            Log.e("zz", _rememberSwitch.value.toString())
            sharedPeferenceProvider.
                saveUserData(_currentUsername.toString(), _currentPassword.toString())
            sharedPeferenceProvider.setRemember(true)
        }
        else{
            sharedPeferenceProvider.deleteUserData()
            sharedPeferenceProvider.setRemember(false)
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
