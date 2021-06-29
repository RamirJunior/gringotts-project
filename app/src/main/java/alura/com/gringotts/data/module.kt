package alura.com.gringotts.data

import alura.com.gringotts.presentation.LoginViewModel
import alura.com.gringotts.presentation.OnboardingViewModel
import alura.com.gringotts.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
}
val sharedPreferencesIMPlModule = module{
    single <SharedPreferencesProvider>{
        SharedPreferencesIMPL(get())
    }
}

val initialRepository = module {
    single {
        InitialRepository(get())
    }
}

val onboardingViewModel = module {
    viewModel {
        OnboardingViewModel(get())
    }
}

val splashViewModel = module {
    viewModel {
        SplashViewModel(get())
    }
}