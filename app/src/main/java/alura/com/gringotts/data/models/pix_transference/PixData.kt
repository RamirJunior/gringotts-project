package alura.com.gringotts.data.models.pix_transference

import com.google.gson.annotations.SerializedName

data class PixData(
    @SerializedName("pix") val pixKey: String,
    @SerializedName("description") val description: String,
    @SerializedName("organization") val organization: String,
    @SerializedName("pix_value") val pixValue: String,
    @SerializedName("pix_token") val pixToken: String
)
