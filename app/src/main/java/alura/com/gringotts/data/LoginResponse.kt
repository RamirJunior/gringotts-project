package alura.com.gringotts.data

data class LoginResponse(
    val user: User, val token_authentication: String,
    val refresh_token: String
)
