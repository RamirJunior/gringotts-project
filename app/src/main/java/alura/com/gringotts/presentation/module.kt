package alura.com.gringotts.presentation

import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.SessionManagerImpl
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.repositories.AccountStatementRepository
import alura.com.gringotts.data.repositories.HomeRepository
import alura.com.gringotts.data.repositories.LoginRepository
import alura.com.gringotts.presentation.home.AccountStatementViewModel
import alura.com.gringotts.presentation.home.HomeViewModel
import alura.com.gringotts.presentation.initial.LoginViewModel
import alura.com.gringotts.presentation.initial.OnboardingViewModel
import alura.com.gringotts.presentation.initial.SplashViewModel
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
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
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        AccountStatementViewModel(get())
    }
    single<SessionManager> {
        SessionManagerImpl(get())
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
    }
    factory {
        LoginRepository(get())
    }
    factory {
        HomeRepository(get(), get())
    }
    factory {
        AccountStatementRepository(get(), get())
    }
    factory {
        ApiInterface.create()
    }
}
