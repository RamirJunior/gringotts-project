package alura.com.gringotts.data.model

import com.google.gson.annotations.SerializedName

data class Token(@SerializedName("token_authentication") val tokenAuthentication: String)
