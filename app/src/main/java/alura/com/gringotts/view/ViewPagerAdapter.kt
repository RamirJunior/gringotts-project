package alura.com.gringotts.view

import alura.com.gringotts.view.pageview.PageOneFragment
import alura.com.gringotts.view.pageview.PageThreeFragment
import alura.com.gringotts.view.pageview.PageTwoFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentBase: Fragment) : FragmentStateAdapter(fragmentBase) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                PageOneFragment()
            }
            1 -> {
                PageTwoFragment()
            }
            2 -> {
                PageThreeFragment()
            }
            else -> {
                throw Exception("Esta página não foi encontrada.")
            }
        }
    }

}