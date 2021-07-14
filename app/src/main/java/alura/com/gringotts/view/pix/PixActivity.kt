package alura.com.gringotts.view.pix

import alura.com.gringotts.databinding.ActivityPixBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PixActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPixBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPixBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    
}
