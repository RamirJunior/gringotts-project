package alura.com.gringotts.presentation
import alura.com.gringotts.data.InitialRepository
import androidx.lifecycle.ViewModel

class SplashViewModel (val initialRepository: InitialRepository) : ViewModel() {

    fun getFinished(): Boolean { //Verifica se o app já foi aberto alguma vez
        return initialRepository.getFinished()
    }

}