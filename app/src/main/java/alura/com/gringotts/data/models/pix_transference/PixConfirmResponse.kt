package alura.com.gringotts.data.models.pix_transference

import com.google.gson.annotations.SerializedName

data class PixConfirmResponse(
    val user: UserPix,
    val pix: String,
    val description: String,
    val organization: String,
    @SerializedName("pix_value") val pixValue: String,
    val date: String
)
