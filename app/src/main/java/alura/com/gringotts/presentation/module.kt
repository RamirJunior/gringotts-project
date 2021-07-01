package alura.com.gringotts.presentation

import alura.com.gringotts.data.repositories.LoginRepository
import alura.com.gringotts.data.SessionManager
import alura.com.gringotts.data.SessionManagerImpl
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
    single<SessionManager> {
        SessionManagerImpl(get())
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
    }
    factory {
        LoginRepository(get())
    }
}
