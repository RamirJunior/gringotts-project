package alura.com.gringotts.data.models.home

import com.google.gson.annotations.SerializedName

data class Benefit(
    @SerializedName("indicator_color") val indicatorColor: String,
    @SerializedName("image") val image: String,
    @SerializedName("title") val title: String,
    @SerializedName("message") val message: String,
    @SerializedName("text_link") val textLink: String
)
