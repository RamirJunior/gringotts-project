package alura.com.gringotts.data.model

data class HomeResponse(
    val balance: Balance, val listaBenefits: MutableList<Benefits>
)
