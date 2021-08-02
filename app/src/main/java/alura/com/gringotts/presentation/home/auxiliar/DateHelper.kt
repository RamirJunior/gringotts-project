package alura.com.gringotts.presentation.home.auxiliar

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    fun formatDate(date: Date, isUtc: Boolean = false): String {
        val formatter = SimpleDateFormat(DATE_FORMAT_dd_mm_yyyy, Locale.getDefault())
        if (isUtc) {
            formatter.timeZone = TimeZone.getTimeZone("UTC")
        }
        return formatter.format(date)
    }

    fun getDateFromString(date: String): Date {
        val formatter = SimpleDateFormat(DATE_FORMAT_dd_mm_yyyy, Locale.getDefault())
        return formatter.parse(date)!!
    }

    fun getMonthString(calendar: Calendar): String {
        val formatter = SimpleDateFormat(DATE_FORMAT_MM, Locale.getDefault())
        return formatter.format(calendar.time).substring(0, 4).uppercase() +
                (formatter.format(calendar.time).substring(4))
    }

    private const val DATE_FORMAT_dd_mm_yyyy: String = "dd/MM/yyyy"
    private const val DATE_FORMAT_MM = "dd MMMM"

}
