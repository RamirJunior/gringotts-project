package alura.com.gringotts.data

class LoginRepository {

    fun login(username: String, password: String): Boolean {
        // handle login
        val result = dataSource.login(username, password) //Ainda n sabemos o dataSource

        return result
    }

}