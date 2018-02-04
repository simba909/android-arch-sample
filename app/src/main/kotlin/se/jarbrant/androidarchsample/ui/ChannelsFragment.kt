package se.jarbrant.androidarchsample.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.ui.lists.CurrentEpisodesAdapter
import se.jarbrant.androidarchsample.utils.extensions.getViewModel
import se.jarbrant.androidarchsample.viewmodels.ChannelViewModel

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

        val viewModel = getViewModel(ChannelViewModel::class.java)
        viewModel.currentEpisodes.observe(this, Observer { episodes ->
            if (episodes != null) {
                Log.d(TAG, "Setting data on currentEpisodesAdapter...")
                currentEpisodesAdapter.items = episodes
            }
        })
    }

    companion object {
        val TAG: String = ChannelsFragment::class.java.simpleName
    }
}
