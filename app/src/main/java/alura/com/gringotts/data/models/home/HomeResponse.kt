package alura.com.gringotts.data.models.home

data class HomeResponse(
    val balance: Balance,
    val benefits: List<Benefit>
)
