package alura.com.gringotts.presentation

import alura.com.gringotts.data.LoginRepository.LoginRepository
import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.SessionManagerImpl
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
    single<SessionManager> {
        SessionManagerImpl(get())
    }
    factory {
        LoginRepository(get())
    }
}
