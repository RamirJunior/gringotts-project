package alura.com.gringotts.data.model

import com.google.gson.annotations.SerializedName

data class Benefits(
    @SerializedName("indicator_color")val indicatorColor: String,
    @SerializedName("image")val image: String,
    @SerializedName("title")val title: String,
    @SerializedName("message")val message: String,
    @SerializedName("text_link")val textLink: String)
