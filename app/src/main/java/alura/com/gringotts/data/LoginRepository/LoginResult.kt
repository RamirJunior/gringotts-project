package alura.com.gringotts.data.LoginRepository

import alura.com.gringotts.data.model.LoginResponse

sealed class LoginResult <out R>{
    data class Success<out T>(val response: T): LoginResult<T>()
    data class Error(val error: String): LoginResult<String>()
    data class ConectionError(val error: String): LoginResult<String>()
}