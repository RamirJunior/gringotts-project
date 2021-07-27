package alura.com.gringotts.data.models.pix_transference;

import com.google.gson.annotations.SerializedName;

data class PixConfirmation (
    @SerializedName("email") val email: String,
    @SerializedName("type") val type: String,
    @SerializedName("description") val description: String,
    @SerializedName("value") val value: Double,
    @SerializedName("date") val date: String
)
