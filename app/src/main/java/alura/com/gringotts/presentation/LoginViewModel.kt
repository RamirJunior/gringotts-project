package alura.com.gringotts.presentation

import alura.com.gringotts.data.LoginRepository.LoginRepository
import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.model.LoginResponse
import alura.com.gringotts.data.model.Token
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private var currentUsername: String = ""
    private var currentPassword: String = ""
    private val _rememberSwitch = MutableLiveData<Boolean>()
    val rememberSwitch: LiveData<Boolean> = _rememberSwitch
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> = _loginError
    private val _loginSuccess = MutableLiveData<Unit>()
    val loginSuccess: LiveData<Unit> = _loginSuccess

    init {
        val user = loginRepository.getUser()
        if (user != null) {
            currentPassword = user.password
            currentUsername = user.email
            _rememberSwitch.postValue(true)
        }
    }

    private fun loginValidation() {
        if (isPasswordValid()) {
            doLogin()
        } else {
            _loginError.postValue("A senha deve conter pelo menos 6 letras")
            _loading.postValue(false)
        }
    }

    private fun doLogin() {
        viewModelScope.launch {
            try {
                val response = loginRepository.userLogin(
                    LoginPayload(currentUsername, currentPassword)
                )
                if (response.isSuccessful) {
                    loginSuccessHandler(response.body()!!)
                } else {
                    loginFailedHandler(response.code())
                }
            } catch (e: Exception) {
                if (e is UnknownHostException) _loginError.postValue("Sem acesso a internet")
            }
            _loading.postValue(false)
        }
    }

    private fun loginSuccessHandler(responseBody: LoginResponse) {
        loginRepository.saveTokens(
            Token(responseBody.tokenAuthentication, responseBody.refreshToken)
        )
        if (_rememberSwitch.value == true) {
            loginRepository.saveUser(LoginPayload(currentUsername, currentPassword))
        } else {
            loginRepository.deleteUserData()
        }
    }

    private fun loginFailedHandler(code: Int) {
        when (code) {
            NOT_FOUND_EMAIL -> {
                _loginError.postValue("Senha e e-mail não encontrados")
            }
            INCOMPATIBLE_EMAIL_PASSWORD -> {
                _loginError.postValue("e-mail incompativel com a senha")
            }
            INCORRECT_PASSWORD -> {
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

    fun getUsername() = currentUsername

    fun getPassword() = currentPassword

    fun onLoginButtonClicked() {
        _loading.postValue(true)
        loginValidation()
    }

    private fun isPasswordValid(): Boolean {
        return currentPassword.length >= 6
    }

    companion object {
        private const val NOT_FOUND_EMAIL = 422
        private const val INCORRECT_PASSWORD = 401
        private const val INCOMPATIBLE_EMAIL_PASSWORD = 404
    }
}
