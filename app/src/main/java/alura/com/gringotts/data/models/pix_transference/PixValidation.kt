package alura.com.gringotts.data.models.pix_transference;

data class PixValidation(
    val email: String,
    val type: String,
    val description: String,
    val value: Double,
    val date: String
)
