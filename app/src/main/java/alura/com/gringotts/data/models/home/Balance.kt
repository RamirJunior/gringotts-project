package alura.com.gringotts.data.models.home

import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("current_value") val currentValue: Double,
    @SerializedName("receivables") val receivables: Double
)
