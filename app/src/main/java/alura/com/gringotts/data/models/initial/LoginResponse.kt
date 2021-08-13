package alura.com.gringotts.data.models.initial

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val user: User,
    @SerializedName("token") val tokenAuthentication: String
)
