package alura.com.gringotts.presentation

import AccountStatementRepository
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.repositories.home.HomeRepository
import alura.com.gringotts.data.repositories.initial.LoginRepository
import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.data.session.SessionManagerImpl
import alura.com.gringotts.presentation.home.AccountStatementViewModel
import alura.com.gringotts.presentation.home.HomeServicesViewModel
import alura.com.gringotts.presentation.home.HomeViewModel
import alura.com.gringotts.presentation.initial.LoginViewModel
import alura.com.gringotts.presentation.initial.OnboardingViewModel
import alura.com.gringotts.presentation.initial.SplashViewModel
import alura.com.gringotts.presentation.pix.OnboardingPixViewModel
import alura.com.gringotts.presentation.pix_transference.InsertEmailPixViewModel
import alura.com.gringotts.presentation.pix_transference.InsertOptionalDescriptionPixViewModel
import alura.com.gringotts.presentation.pix_transference.PixSharedViewModel
import alura.com.gringotts.presentation.pix_transference.PixValueViewModel
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
    viewModel {
        OnboardingPixViewModel(get())
    }
    viewModel {
        HomeServicesViewModel(get())
    }
    viewModel {
        PixSharedViewModel()
    }
    viewModel {
        InsertEmailPixViewModel(get())
    }
    viewModel {
        InsertOptionalDescriptionPixViewModel(get())
    }
    viewModel {
        PixValueViewModel(get())
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
