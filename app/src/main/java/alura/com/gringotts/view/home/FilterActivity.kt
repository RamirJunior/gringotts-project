package alura.com.gringotts.view.home

import alura.com.gringotts.databinding.ActivityFilterBinding
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class FilterActivity : AppCompatActivity() {
    private var _binding: ActivityFilterBinding? = null
    private val binding: ActivityFilterBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        _binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}