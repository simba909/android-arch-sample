package se.jarbrant.androidarchsample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import se.jarbrant.androidarchsample.data.Episode
import se.jarbrant.androidarchsample.repositories.EpisodeRepository

/**
 * @author Simon Jarbrant
 * Created on 2018-02-03.
 */
class EpisodesViewModel : ViewModel() {

    private val repository = EpisodeRepository

    private val _popularEpisodes = MutableLiveData<List<Episode>>()
    private var popularEpisodesDisposable: Disposable? = null

    val popularEpisodes: LiveData<List<Episode>> = _popularEpisodes

    init {
        Log.d(TAG, "Setting up...")

        repository.getPopularEpisodes()
                .cache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { episodes, error ->
                    if (episodes != null) {
                        _popularEpisodes.value = episodes
                    } else if (error != null) {
                        Log.w(TAG, "Failed to fetch popular episodes :(", error)
                    }
                }
                .let { popularEpisodesDisposable = it }
    }

    override fun onCleared() {
        Log.d(TAG, "Clearing")
        popularEpisodesDisposable?.dispose()
    }

    companion object {
        private val TAG: String = EpisodesViewModel::class.java.simpleName
    }
}
