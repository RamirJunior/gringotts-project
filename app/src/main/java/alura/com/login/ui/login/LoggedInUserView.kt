package alura.com.login.ui.login

/**
 * Tela para aramazenar dados pegos do banco de dados que usaremos na Interface do Usuário
 * como por exemplo o nome, depois que o usuário loga
 */
data class LoggedInUserView(
        val displayName: String
        //... other data fields that may be accessible to the UI
)