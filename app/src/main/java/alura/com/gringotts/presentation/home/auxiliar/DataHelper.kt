package alura.com.gringotts.presentation.home.auxiliar

import java.text.SimpleDateFormat
import java.util.*

class DataHelper {

    companion object {

        fun formatDate(date: Date): String {
            val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            return formatter.format(date)
        }

        fun getDateFromString(dateString: String): Date {
            val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            return formatter.parse(dateString)!!
        }

        private const val DATE_FORMAT: String = "dd/MM/yyyy"
    }

}
