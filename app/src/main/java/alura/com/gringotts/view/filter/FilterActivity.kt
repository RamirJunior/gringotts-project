package alura.com.gringotts.view.filter

import alura.com.gringotts.databinding.ActivityFilterBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FilterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
