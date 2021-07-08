package alura.com.gringotts.data.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("status") val status: String,
    @SerializedName("time") val time: String,
    @SerializedName("type") val type: String,
    @SerializedName("type_description") val typeDescription: String,
    @SerializedName("value") val value: String
)