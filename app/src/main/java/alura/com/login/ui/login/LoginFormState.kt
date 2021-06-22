package alura.com.login.ui.login

/**
 * Validação do estado dos dados da nossa tela de login
 */
data class LoginFormState(val usernameError: Int? = null,
                          val passwordError: Int? = null,
                          val isDataValid: Boolean = false)