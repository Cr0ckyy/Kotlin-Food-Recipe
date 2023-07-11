package romilp.foody.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    private val resultBundle: Bundle,
    private val fragments: ArrayList<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        // Return the total number of fragments
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        // Create a new fragment instance and pass the resultBundle as arguments
        fragments[position].arguments = resultBundle
        return fragments[position]
    }
}
