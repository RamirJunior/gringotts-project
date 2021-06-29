package alura.com.gringotts.presentation

import alura.com.gringotts.data.InitialRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingViewModel(val initialRepository: InitialRepository) : ViewModel() {

    fun onBoardingFinished() { //Salva que a onboard foi executada para não ter que repetir
        initialRepository.setFinished()
    }
}