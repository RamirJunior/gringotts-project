package alura.com.gringotts.data.models.initial

import com.google.gson.annotations.SerializedName

data class LoginPayload(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
