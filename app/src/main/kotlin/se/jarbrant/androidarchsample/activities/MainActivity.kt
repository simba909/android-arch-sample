package se.jarbrant.androidarchsample.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import se.jarbrant.androidarchsample.fragments.ChannelsFragment
import se.jarbrant.androidarchsample.fragments.PopularEpisodesFragment

class MainActivity : AppCompatActivity() {

    private val channelsFragment = ChannelsFragment()
    private val popularEpisodesFragment = PopularEpisodesFragment()

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.main_bottom_navigation)

        // Do initial fragment setup...
        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, channelsFragment)
                .commit()

        with(bottomNavigationView) {
            setOnNavigationItemReselectedListener {
                // TODO -> React to this event...
            }
            setOnNavigationItemSelectedListener {
                handleNavigation(it)
                true
            }
        }
    }

    private fun handleNavigation(selectedItem: MenuItem) {
        val transaction = supportFragmentManager.beginTransaction()

        when (selectedItem.itemId) {
            R.id.main_navigation_channels -> {
                if (supportFragmentManager.findFragmentByTag(PopularEpisodesFragment.TAG) != null) {
                    transaction.detach(popularEpisodesFragment)
                }

                transaction.attach(channelsFragment)
            }
            R.id.main_navigation_popular_episodes -> {
                transaction.detach(channelsFragment)

                if (supportFragmentManager.findFragmentByTag(PopularEpisodesFragment.TAG) != null) {
                    // We already have the popular fragment in the FragmentManager, only
                    // need to attach it again
                    transaction.attach(popularEpisodesFragment)
                } else {
                    transaction.add(
                            R.id.main_container,
                            popularEpisodesFragment,
                            PopularEpisodesFragment.TAG
                    )
                }
            }
        }

        transaction.commit()
    }
}
