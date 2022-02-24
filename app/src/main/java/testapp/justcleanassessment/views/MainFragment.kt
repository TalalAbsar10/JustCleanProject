package testapp.justcleanassessment.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import testapp.justcleanassessment.R
import testapp.justcleanassessment.databinding.FragmentMainBinding
import testapp.justcleanassessment.util.ViewPagerAdater

class MainFragment : Fragment(), LifecycleOwner {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout
        val pagerAdapter = ViewPagerAdater(activity as FragmentActivity )

        viewPager.adapter = pagerAdapter
        TabLayoutMediator(
            tabLayout,
            viewPager,
        ) { tab, position ->
            val tabNames = listOf("Posts", "Favorites")
            tab.text = tabNames[position]
        }.attach()

        return binding.root
    }

}