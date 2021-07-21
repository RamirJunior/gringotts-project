package alura.com.gringotts.data.models.initial

import com.google.gson.annotations.SerializedName

data class Token(@SerializedName("token_authentication") val tokenAuthentication: String)
