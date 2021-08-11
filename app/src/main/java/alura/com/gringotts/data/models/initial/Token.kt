package alura.com.gringotts.data.models.initial

import com.google.gson.annotations.SerializedName

data class Token(@SerializedName("token") val tokenAuthentication: String)
