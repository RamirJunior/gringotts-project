package alura.com.gringotts.presentation

import alura.com.gringotts.data.exceptions.IncorrectPasswordException
import alura.com.gringotts.data.exceptions.NotFoundEmailException
import alura.com.gringotts.data.model.LoginPayload
import alura.com.gringotts.data.repositories.LoginRepository
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
        } else {
            _rememberSwitch.postValue(false)
        }
    }

    private fun loginValidation() {
        if (isPasswordValid()) {
            doLogin()
        } else {
            _loginError.postValue("A senha deve conter pelo menos 6 letras")
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
            } catch (e: IncorrectPasswordException) {
                _loginError.postValue(e.message)
            } catch (e: NotFoundEmailException) {
                _loginError.postValue(e.message)
            } catch (e: Exception) {
                if (e is UnknownHostException)
                    _loginError.postValue("Sem acesso a internet")
                else
                    _loginError.postValue("Erro desconhecido")
            }
            _loading.postValue(false)
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
        loginValidation()
    }

    private fun isPasswordValid(): Boolean {
        return currentPassword.length >= 6
    }

}