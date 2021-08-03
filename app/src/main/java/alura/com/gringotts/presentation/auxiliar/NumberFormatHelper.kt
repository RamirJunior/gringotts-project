package alura.com.gringotts.presentation.auxiliar

import java.text.NumberFormat

object NumberFormatHelper {
    fun formatDoubleToTwoFractionDigits(value: Double): String{
        val numberFormatter = NumberFormat.getInstance()
        numberFormatter.minimumFractionDigits = 2
        numberFormatter.maximumFractionDigits = 2
        return  numberFormatter.format(value)
    }
}