package alura.com.gringotts.data.models.pix_transference

import com.google.gson.annotations.SerializedName

data class PixData(
    @SerializedName("email") val email: String,
    @SerializedName("description") val description: String,
    @SerializedName("organization") val organization: String,
    @SerializedName("pix_value") val pixValue: Float,
    @SerializedName("pix_token") val pixToken: String
)
