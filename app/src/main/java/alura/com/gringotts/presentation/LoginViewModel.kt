package alura.com.gringotts.presentation

import alura.com.gringotts.data.model.LoginResponse
import alura.com.gringotts.data.SharedPreferencesProvider
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.model.LoginModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


//Onde vamos realizar as verificaçoes
class LoginViewModel() : ViewModel() {
    private var _currentUsername: String? = ""
    private var usernameIsValid: Boolean = true
    private var _currentPassword :String? = ""
    private var passwordIsValid: Boolean = true
    private val _rememberSwitch = MutableLiveData<Boolean>()
    val rememberSwitch: LiveData<Boolean> = _rememberSwitch
    private lateinit var sharedPeferenceIMPL: SharedPreferencesProvider

    fun loginValidation(){
        val apiInterface = ApiInterface.create().userLogin(LoginModel(_currentUsername.toString(), _currentPassword.toString()))
        apiInterface.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if(response?.isSuccessful==true){
                    Log.e("user", response.body()!!.user.firstName)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //Log.e("erro", t.message.toString())
            }
        })
    }

    fun init(IMPL: SharedPreferencesProvider) {
        sharedPeferenceIMPL=IMPL
        _rememberSwitch.value = sharedPeferenceIMPL.getRemeber()
        if(_rememberSwitch.value == null) {
            _rememberSwitch.value = false
        }
        //Log.e("estadoSwitch", _rememberSwitch.value.toString())
        if(_rememberSwitch.value==true) {
            _currentUsername = sharedPeferenceIMPL.getUsername()
            _currentPassword = sharedPeferenceIMPL.getPassword()
        }
    }

    fun switchClicked(value: Boolean){
        _rememberSwitch.postValue(value)
    }

    fun setUsername(value: String){
        usernameIsValid=true
        _currentUsername = value
    }

    fun setPassword(value: String){
        passwordIsValid=true
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
            //Log.e("switchOn", _rememberSwitch.value.toString())
            sharedPeferenceIMPL.
            saveUserData(_currentUsername.toString(), _currentPassword.toString())
            sharedPeferenceIMPL.setRemember(true)
        }
        else{
            //Log.e("switchOff", _rememberSwitch.value.toString())
            sharedPeferenceIMPL.deleteUserData()
            sharedPeferenceIMPL.setRemember(false)
        }
        loginValidation()
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
