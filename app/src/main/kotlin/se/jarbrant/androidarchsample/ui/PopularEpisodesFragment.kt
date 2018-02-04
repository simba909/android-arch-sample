package se.jarbrant.androidarchsample.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.jarbrant.androidarchsample.R
import se.jarbrant.androidarchsample.ui.lists.EpisodesAdapter
import se.jarbrant.androidarchsample.utils.extensions.getViewModel
import se.jarbrant.androidarchsample.viewmodels.EpisodesViewModel

/**
 * @author Simon Jarbrant
 * Created on 2018-02-03.
 */
class PopularEpisodesFragment : ToolbarFragment() {

    private lateinit var recyclerView: RecyclerView
    private val episodesAdapter = EpisodesAdapter()

    override val toolbarTitle: String
        get() = getString(R.string.toolbar_title_popular)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_popular_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.popular_episodes_recycler)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = episodesAdapter
            setHasFixedSize(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = getViewModel(EpisodesViewModel::class.java)
        viewModel.popularEpisodes.observe(this, Observer { episodes ->
            if (episodes != null) {
                episodesAdapter.items = episodes
            }
        })
    }

    companion object {
        val TAG: String = PopularEpisodesFragment::class.java.simpleName
    }
}
