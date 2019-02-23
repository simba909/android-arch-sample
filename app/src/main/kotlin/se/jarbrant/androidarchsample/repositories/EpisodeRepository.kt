package se.jarbrant.androidarchsample.repositories

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import se.jarbrant.androidarchsample.networking.Api
import se.jarbrant.androidarchsample.models.Episode

/**
 * @author Simon Jarbrant
 * Created on 2018-02-03.
 */
object EpisodeRepository {

    /**
     * Returns a list of currently popular [Episode]. The returned Single is subscribed
     * on [Schedulers.io], so consumers of this function should remember to switch to another
     * thread as necessary.
     */
    fun getPopularEpisodes(): Single<List<Episode>> {
        return Api.client.getPopularEpisodes(10)
                .subscribeOn(Schedulers.io())
                .map { it.episodes }
    }
}
