package alura.com.gringotts.presentation.auxiliar

import android.util.Patterns

object InputValidationHelper {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}
