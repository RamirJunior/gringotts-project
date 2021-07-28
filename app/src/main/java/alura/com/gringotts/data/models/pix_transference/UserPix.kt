package alura.com.gringotts.data.models.pix_transference

import com.google.gson.annotations.SerializedName

data class UserPix(
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String
)
