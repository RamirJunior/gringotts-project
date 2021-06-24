package alura.com.gringotts.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class InitialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //Gerenciador de Conexão
    //val connectionManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    //val activeNetwork: NetworkInfo? =connectionManager.activeNetworkInfo
    //Valor falso corresponde a ausência de conexão
    //val isConnected:Boolean = activeNetwork?.isConnectedOrConnecting == false

    //Checagem e Layout
    //if (isConnected) {

    //}

    /*override fun onResume() {
        super.onResume()
        username.setText(loginViewModel.currentUsername.value.toString())
        password.setText(loginViewModel.currentPassword.value.toString())
        remember.isChecked = loginViewModel.rememberSwitch.value == true
    }*/
}
