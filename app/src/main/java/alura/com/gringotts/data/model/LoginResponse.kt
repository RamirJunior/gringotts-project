package alura.com.gringotts.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    val user: User,
    @SerializedName("token_authentication") val tokenAuthentication: String,
    @SerializedName("refresh_token")val refreshToken: String
)
