package alura.com.gringotts.data.models.pix_transference

import java.util.*

class Pix {
    lateinit var receiverEmail: String
    var pixValue: Float = 0.0F
    lateinit var receiverName: String
    lateinit var receiverCPF: String
    lateinit var message: String
    lateinit var institution: String
    lateinit var date: Date
}
