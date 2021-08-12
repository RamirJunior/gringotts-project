package alura.com.gringotts.presentation.auxiliar

import java.math.BigDecimal
import java.text.NumberFormat

object NumberFormatHelper {
    fun formatDoubleToTwoFractionDigits(value: Double): String {
        val numberFormatter = NumberFormat.getInstance()
        numberFormatter.minimumFractionDigits = 2
        numberFormatter.maximumFractionDigits = 2
        return numberFormatter.format(value)
    }

    fun formatStringToDouble(value: String): Double {
        return BigDecimal(value.replace("[^0-9]".toRegex(), ""))
            .setScale(2, BigDecimal.ROUND_FLOOR)
            .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR).toDouble()
    }
}
