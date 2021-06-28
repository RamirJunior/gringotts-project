package alura.com.gringotts.data

import alura.com.gringotts.presentation.LoginViewModel
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


