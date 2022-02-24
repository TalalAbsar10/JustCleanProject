package testapp.justcleanassessment.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import testapp.justcleanassessment.views.posts.PostsFragment
import testapp.justcleanproject.view.ui.fragments.favorites.FavoritesFragment

class ViewPagerAdater(
    fragmentActivity: FragmentActivity
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> PostsFragment()
                else -> FavoritesFragment()
            }
        }

        override fun getItemCount(): Int {
            return 2 }
}