package alura.com.gringotts.data.models.pix_transference

import java.io.Serializable

class Pix : Serializable {
    lateinit var receiverEmail: String
    var pixValue: Double = 0.0
    lateinit var institution: String
    lateinit var name: String
    lateinit var message: String
    lateinit var date: String
}
