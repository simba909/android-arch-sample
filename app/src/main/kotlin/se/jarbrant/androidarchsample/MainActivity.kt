package se.jarbrant.androidarchsample

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toolbar
import se.jarbrant.androidarchsample.extensions.getViewModel
import se.jarbrant.androidarchsample.ui.MainAdapter
import se.jarbrant.androidarchsample.ui.SpacingItemDecoration
import se.jarbrant.androidarchsample.viewmodels.ChannelViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(SpacingItemDecoration())
    }

    override fun onStart() {
        super.onStart()

        val viewModel = getViewModel(ChannelViewModel::class.java)
        viewModel.currentEpisodes.observe(this, Observer { episodes ->
            if (episodes != null) {
                Log.d(TAG, "Setting data on adapter...")
                adapter.setData(episodes)
            }
        })
    }

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }
}
