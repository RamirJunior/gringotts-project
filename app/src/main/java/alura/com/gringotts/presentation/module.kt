package alura.com.gringotts.presentation

import AccountStatementRepository
import alura.com.gringotts.data.AccountStatementDatabase
import alura.com.gringotts.data.api.ApiInterface
import alura.com.gringotts.data.models.pix_transference.Pix
import alura.com.gringotts.data.repositories.home.HomeActivityRepository
import alura.com.gringotts.data.repositories.home.HomeRepository
import alura.com.gringotts.data.repositories.initial.LoginRepository
import alura.com.gringotts.data.repositories.pix_transference.PixRepository
import alura.com.gringotts.data.session.SessionManager
import alura.com.gringotts.data.session.SessionManagerImpl
import alura.com.gringotts.presentation.home.AccountStatementViewModel
import alura.com.gringotts.presentation.home.HomeActivityViewModel
import alura.com.gringotts.presentation.home.HomeServicesViewModel
import alura.com.gringotts.presentation.home.HomeViewModel
import alura.com.gringotts.presentation.initial.LoginViewModel
import alura.com.gringotts.presentation.initial.OnboardingViewModel
import alura.com.gringotts.presentation.initial.SplashViewModel
import alura.com.gringotts.presentation.pix.OnboardingPixViewModel
import alura.com.gringotts.presentation.pix_transference.ConfirmationPixViewModel
import alura.com.gringotts.presentation.pix_transference.InsertEmailPixViewModel
import alura.com.gringotts.presentation.pix_transference.InsertOptionalDescriptionPixViewModel
import alura.com.gringotts.presentation.pix_transference.PixValueViewModel
import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
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
        HomeActivityViewModel(get())
    }
    viewModel {
        OnboardingPixViewModel(get())
    }
    viewModel {
        HomeServicesViewModel(get())
    }
    viewModel { (pix: Pix) ->
        InsertEmailPixViewModel(pix)
    }
    viewModel { (pix: Pix) ->
        InsertOptionalDescriptionPixViewModel(pix)
    }
    viewModel { (pix: Pix) ->
        PixValueViewModel(pix, get())
    }
    viewModel { (pix: Pix) ->
        ConfirmationPixViewModel(pix, get())
    }
    single<SessionManager> {
        SessionManagerImpl(get())
    }
    single<SharedPreferences> {
        androidContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
    }
    factory {
        LoginRepository(get(), get())
    }
    factory {
        HomeRepository(get(), get())
    }
    factory {
        HomeActivityRepository(get())
    }
    single {
        AccountStatementDatabase.provideDatabase(androidApplication())
    }
    factory {
        get<AccountStatementDatabase>().accountStatementDAO()
    }
    factory {
        AccountStatementRepository(get(), get())
    }
    factory {
        ApiInterface.create(get())
    }
    factory {
        PixRepository(get(), get())
    }

}
