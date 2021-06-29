package alura.com.gringotts.presentation

import alura.com.gringotts.data.LoginRepository.LoginRepository
import alura.com.gringotts.data.SharedPreferencesProvider
import alura.com.gringotts.data.model.LoginModel
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.net.ConnectException


//Onde vamos realizar as verificaçoes
class LoginViewModel(val sharedPeferenceIMPL: SharedPreferencesProvider, private val loginRepository: LoginRepository) : ViewModel() {
    private var currentUsername: String? = ""
    private var currentPassword: String? = ""
    private val _rememberSwitch = MutableLiveData<Boolean>()
    val rememberSwitch: LiveData<Boolean> = _rememberSwitch
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> = _loginError
    private val _loginSuccess = MutableLiveData<Unit>()
    val loginSucces: LiveData<Unit> = _loginSuccess
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    init{
        _rememberSwitch.value = sharedPeferenceIMPL.getRemeber() == true
        if (_rememberSwitch.value == null) {
            _rememberSwitch.value = false
        }
        else if (_rememberSwitch.value == true) {
            currentUsername = sharedPeferenceIMPL.getUsername()
            currentPassword = sharedPeferenceIMPL.getPassword()
        }
    }

    private fun loginValidation(){

    }

    private fun doLogin() {
        viewModelScope.launch{
             try{
                 val response =loginRepository.userLogin(
                        LoginModel(currentUsername.toString(), currentPassword.toString()))
                 if(response.isSuccessful) {
                     _loginSuccess.postValue(Unit)
                     //salvar state usuario
                 }
                 else{
                     loginFailedHandler(response.code())
                 }
            }catch (e: ConnectException) {
                _loginError.postValue("Sem acesso a internet")
            }
        }
        _loading.postValue(false)
    }

    private fun loginFailedHandler(code: Int) {
        _loginResult.postValue(false)
        when (code) {
            422 -> {
                _loginError.postValue("Senha e e-mail não encontrados")
            }
            404 -> {
                _loginError.postValue("e-mail incompativel com a senha")
            }
            401 -> {
                _loginError.postValue("Senha incorreta")
            }
        }
    }

    fun switchChanged(value: Boolean) {
        _rememberSwitch.postValue(value)
    }

    fun setUsername(value: String) {
        currentUsername = value
    }

    fun setPassword(value: String) {
        currentPassword = value
    }


    fun getUsername(): String {
        if (currentUsername.equals("")) {
            return ""
        }
        return currentUsername.toString()
    }

    fun getPassword(): String {
        if (currentPassword.equals("")) {
            return ""
        }
        return currentPassword.toString()
    }

    private fun saveUserData(){
        if (_rememberSwitch.value == true) {
            Log.e("switchOn", _rememberSwitch.value.toString())
            sharedPeferenceIMPL.saveUserData(
                currentUsername.toString(),
                currentPassword.toString()
            )
            sharedPeferenceIMPL.setRemember(true)
        } else {
            Log.e("switchOff", _rememberSwitch.value.toString())
            sharedPeferenceIMPL.deleteUserData()
            sharedPeferenceIMPL.setRemember(false)
        }
    }

    fun login() {
        _loading.postValue(true)
        doLogin()
    }

    // Método para Validar a senha utilizando Pattern e Matcher
    private fun isPasswordValid(): Boolean {
        val password = currentPassword.toString()
        var resp=true
        if(password.length<6) resp = false
        return resp
    }

    companion object{
        private const val Senha
    }
}
