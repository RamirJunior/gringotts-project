package alura.com.gringotts.data.model

data class LoginResponse(
    val user: User, val token_authentication: String,
    val refresh_token: String
)
