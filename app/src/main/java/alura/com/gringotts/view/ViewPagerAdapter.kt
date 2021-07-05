package alura.com.gringotts.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.Exception

class ViewPagerAdapter(fragmentBase: Fragment): FragmentStateAdapter(fragmentBase) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                PageOneFragment()
            }
            1 -> {
                PageTwoFragment()
            }
            2 -> {
                PageThreeFragment()
            }
            else {
                throw Exception("Esta página não foi encontrada.")
            }
        }
    }

}