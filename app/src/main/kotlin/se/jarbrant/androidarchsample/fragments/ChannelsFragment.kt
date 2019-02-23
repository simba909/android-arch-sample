package se.jarbrant.androidarchsample.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.extensions.TAG
import se.jarbrant.androidarchsample.viewmodels.ChannelViewModel
import se.jarbrant.androidarchsample.viewmodels.factories.ViewModelFactory
import se.jarbrant.androidarchsample.views.SpacingItemDecoration
import se.jarbrant.androidarchsample.views.lists.CurrentEpisodesAdapter

/**
 * @author Simon Jarbrant
 * Created on 2018-02-03.
 */
class ChannelsFragment : ToolbarFragment() {

    private lateinit var recyclerView: RecyclerView
    private val currentEpisodesAdapter = CurrentEpisodesAdapter()

    override val toolbarTitle: String
        get() = getString(R.string.toolbar_title_channels)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_channels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = currentEpisodesAdapter

            setHasFixedSize(true)
            addItemDecoration(SpacingItemDecoration())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModelFactory = ViewModelFactory()
        val viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ChannelViewModel::class.java)

        viewModel.currentEpisodes.observe(this, Observer { episodes ->
            if (episodes != null) {
                Log.d(TAG, "Setting data on currentEpisodesAdapter...")
                currentEpisodesAdapter.items = episodes
            }
        })
    }
}
