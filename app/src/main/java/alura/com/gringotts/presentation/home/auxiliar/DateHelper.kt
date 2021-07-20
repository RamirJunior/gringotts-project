package alura.com.gringotts.presentation.home.auxiliar

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat(DATE_FORMAT_dd_mm_yyyy, Locale.getDefault())
        return formatter.format(date)
    }

    fun getDateFromString(date: String): Date {
        val formatter = SimpleDateFormat(DATE_FORMAT_dd_mm_yyyy, Locale.getDefault())
        return formatter.parse(date)!!
    }

    fun getMonthString(calendar: Calendar): String {
        val formatter = SimpleDateFormat(DATE_FORMAT_MM, Locale("pt", "br"))
        return formatter.format(calendar.time)
    }

    private const val DATE_FORMAT_dd_mm_yyyy: String = "dd/MM/yyyy"
    private const val DATE_FORMAT_MM = "MMMM"

}
