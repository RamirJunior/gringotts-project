package alura.com.gringotts.presentation.initial

import alura.com.gringotts.data.exceptions.NotFoundEmailException
import alura.com.gringotts.data.models.initial.LoginPayload
import alura.com.gringotts.data.repositories.initial.LoginRepository
import alura.com.gringotts.presentation.auxiliar.InputValidationHelper.isEmailValid
import alura.com.gringotts.presentation.auxiliar.SingleLiveEvent
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
    private val _usernameError = MutableLiveData<String?>()
    val usernameError: LiveData<String?> = _usernameError
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> = _passwordError
    private val _digitalSwitch = SingleLiveEvent<Unit>()
    val digitalSwitch: LiveData<Unit> = _digitalSwitch

    init {
        val user = loginRepository.getUser()
        if (user != null) {
            currentPassword = user.password
            currentUsername = user.email
            _rememberSwitch.postValue(true)
        } else {
            _rememberSwitch.postValue(false)
        }
    }

    private fun loginValidation() {
        val isPasswordValid = isPasswordValid()
        val isEmailValid = emailValidation()
        if (isPasswordValid && isEmailValid) {
            doLogin()
        }
    }

    private fun doLogin() {
        _loading.postValue(true)
        viewModelScope.launch {
            try {
                loginRepository.userLogin(
                    LoginPayload(currentUsername, currentPassword),
                    _rememberSwitch.value!!
                )
                _loginSuccess.postValue(Unit)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException -> _loginError.postValue("Sem acesso a internet")
                    is NotFoundEmailException -> _loginError.postValue(e.message)
                    else -> _loginError.postValue("Erro desconhecido")
                }
            }
            _loading.postValue(false)
        }
    }

    fun switchChanged(value: Boolean) {
        _rememberSwitch.postValue(value)
        if (value && currentPassword != "" && currentUsername != "" && isPasswordValid() && emailValidation())
            _digitalSwitch.postValue(Unit)
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
        loginValidation()
    }

    private fun isPasswordValid(): Boolean {
        val isPasswordValid = currentPassword.length >= 6
        if (!isPasswordValid) {
            _passwordError.postValue("A senha deve conter pelo menos 6 letras")
        } else {
            _passwordError.postValue(null)
        }
        return isPasswordValid
    }

    private fun emailValidation(): Boolean {
        val isEmailValid = isEmailValid(currentUsername)
        if (!isEmailValid) {
            _usernameError.postValue("E-mail inválido")
        } else {
            _usernameError.postValue(null)
        }
        return isEmailValid
    }
}
