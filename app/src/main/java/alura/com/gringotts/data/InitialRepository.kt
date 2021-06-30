package alura.com.gringotts.data

class InitialRepository(private val sessionManager: SessionManager) {

    fun setFinished() {
        sessionManager.setFinished()
    }

    fun getFinished(): Boolean {
        return sessionManager.getFinished()
    }

}