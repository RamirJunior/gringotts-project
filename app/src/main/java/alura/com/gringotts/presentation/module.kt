package alura.com.gringotts.data

import alura.com.gringotts.data.LoginRepository.LoginRepository
import alura.com.gringotts.presentation.LoginViewModel
import alura.com.gringotts.presentation.OnboardingViewModel
import alura.com.gringotts.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val initialModule = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        OnboardingViewModel(get())
    }
    viewModel {
        SplashViewModel(get())
    }
    single <SessionManager>{
        SessionManagerImpl(get())
    }
    factory {
        LoginRepository(get())
    }
}
