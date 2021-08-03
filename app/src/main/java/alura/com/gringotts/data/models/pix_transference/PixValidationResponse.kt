package alura.com.gringotts.data.models.pix_transference

import com.google.gson.annotations.SerializedName

data class PixValidationResponse(
    val user: UserPix,
    val pix: String,
    val description: String,
    val organization: String,
    @SerializedName("pix_value") val pixValue: String,
    @SerializedName("pix_token") val pixToken: String,
    val date: String
)
