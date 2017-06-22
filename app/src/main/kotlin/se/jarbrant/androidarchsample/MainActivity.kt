package se.jarbrant.androidarchsample

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import se.jarbrant.androidarchsample.ui.MainAdapter
import se.jarbrant.androidarchsample.viewmodels.ChannelViewModel

class MainActivity : LifecycleActivity() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        val viewModel = ViewModelProviders.of(this).get(ChannelViewModel::class.java)
        viewModel.nationalChannels.observe(this, Observer { channels ->
            if (channels != null) {
                Log.d(TAG, "Setting data on adapter...")
                adapter.setData(channels)
            } else {
                Log.w(TAG, "Got null channels from the channels viewmodel :(")
            }
        })
    }

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }
}
