package alura.com.gringotts.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class InitialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*override fun onResume() {
        super.onResume()
        username.setText(loginViewModel.currentUsername.value.toString())
        password.setText(loginViewModel.currentPassword.value.toString())
        remember.isChecked = loginViewModel.rememberSwitch.value == true
    }*/
}
