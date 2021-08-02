package alura.com.gringotts.data.models.pix_transference

import java.io.Serializable

data class Pix(
    var receiverEmail: String,
    var pixValue: Double,
    var institution: String,
    var name: String,
    var message: String,
    var date: String
) : Serializable
