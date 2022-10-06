package rs.raf.jul.lazar_radovanovic_rn8919.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.fragments.DiscoverFragment
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.fragments.ProfileFragment

class TabAdapter (fm: FragmentManager) :  FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return DiscoverFragment()
            }
            1 -> {
                return ProfileFragment()
            }
            else -> {
                return ProfileFragment()
            }
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Discover"
            else -> "Profile"
        }
    }
}