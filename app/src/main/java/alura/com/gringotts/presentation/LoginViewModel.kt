package alura.com.gringotts.presentation

import alura.com.gringotts.data.SharedPreferencesProvider
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.LoginModel
import alura.com.gringotts.data.model.LoginResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException
import java.util.regex.Matcher
import java.util.regex.Pattern


//Onde vamos realizar as verificaçoes
class LoginViewModel(val sharedPeferenceIMPL: SharedPreferencesProvider) : ViewModel() {
    private var _currentUsername: String? = ""
    private var _currentPassword: String? = ""
    private val _rememberSwitch = MutableLiveData<Boolean>()
    val rememberSwitch: LiveData<Boolean> = _rememberSwitch
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private lateinit var errorMassage: String
    private lateinit var loginResponse: LoginResponse
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun loginValidation() {
        if(isPasswordValid()) {
            val apiInterface = ApiInterface.create()
                .userLogin(LoginModel(_currentUsername.toString(), _currentPassword.toString()))
            apiInterface.enqueue(object : Callback<LoginResponse> {

                override fun onResponse(
                    call: Call<LoginResponse>?,
                    response: Response<LoginResponse>?
                ) {
                    Log.e("Conexao sucedida", "")
                    if (response?.isSuccessful == true) {
                        loginResponse = response.body()!!
                        sharedPeferenceIMPL.saveResponse(
                            loginResponse.token_authentication,
                            loginResponse.refresh_token
                        )
                        saveUserData()
                        _loginResult.postValue(true)
                    } else {
                        _loginResult.postValue(false)
                        Log.e("error", response?.code().toString())
                        if (response?.code() == 422) {
                            errorMassage = "Senha e e-mail não encontrados"
                        }
                        if (response?.code() == 404) {
                            errorMassage = "e-mail incompativel com a senha"
                        }
                        if (response?.code() == 401) {
                            errorMassage = "Senha incorreta"
                        }
                    }
                    _loading.postValue(false)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("Falha conexao", t.message.toString())
                    if (t is UnknownHostException) {
                        errorMassage = "Sem acesso a internet"
                    }
                    _loginResult.postValue(false)
                    _loading.postValue(false)
                }
            })
        }
        else{
            errorMassage = "A senha deve ter: 6 caracteres ou mais"
            _loginResult.postValue(false)
            _loading.postValue(false)
        }
    }

    init{
        _rememberSwitch.value = sharedPeferenceIMPL.getRemeber()
        _loading.postValue(false)
        if (_rememberSwitch.value == null) {
            _rememberSwitch.value = false
        }
        if (_rememberSwitch.value == true) {
            _currentUsername = sharedPeferenceIMPL.getUsername()
            _currentPassword = sharedPeferenceIMPL.getPassword()
        }
    }

    fun switchClicked(value: Boolean) {
        _rememberSwitch.postValue(value)
    }

    fun setUsername(value: String) {
        _currentUsername = value
    }

    fun setPassword(value: String) {
        _currentPassword = value
    }

    fun getError(): String{
        return errorMassage
    }

    fun getUsername(): String {
        if (_currentUsername.equals("")) {
            return ""
        }
        return _currentUsername.toString()
    }

    fun getPassword(): String {
        if (_currentPassword.equals("")) {
            return ""
        }
        return _currentPassword.toString()
    }

    private fun saveUserData(){
        if (_rememberSwitch.value == true) {
            Log.e("switchOn", _rememberSwitch.value.toString())
            sharedPeferenceIMPL.saveUserData(
                _currentUsername.toString(),
                _currentPassword.toString()
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
        loginValidation()
    }

    // Método para Validar a senha utilizando Pattern e Matcher
    private fun isPasswordValid(): Boolean {
        val password = _currentPassword.toString()
        var resp=true
        if(password.length<6) resp = false
        return resp
    }
}
