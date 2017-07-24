package se.jarbrant.androidarchsample.api.response

import se.jarbrant.androidarchsample.data.CurrentEpisode

/**
 * @author Simon Jarbrant
 * Created on 2017-07-17.
 */
data class CurrentEpisodesResponse(val episodes: List<CurrentEpisode>)
