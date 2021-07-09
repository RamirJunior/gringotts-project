package alura.com.gringotts.view.initial

import alura.com.gringotts.databinding.ActivityInitialBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class InitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
