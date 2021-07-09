package alura.com.gringotts.data.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    val status: String,
    val time: String,
    val type: String,
    val date: String,
    val value: String,
    @SerializedName("type_description") val typeDescription: String
)