package alura.com.gringotts.data.models.home

import com.google.gson.annotations.SerializedName

data class SendFcmTokenPayload(
    @SerializedName("fcm_token") val tokenFcm: String
)
