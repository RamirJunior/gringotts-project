package alura.com.gringotts.view.home.adapters

import alura.com.gringotts.view.home.fragments.HomeServicesFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeServicesAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return HomeServicesFragment().also {
            it.arguments = Bundle().apply {
                putInt(POSITION, position)
            }
        }
    }

    companion object {
        private const val POSITION = "position"
    }

}
