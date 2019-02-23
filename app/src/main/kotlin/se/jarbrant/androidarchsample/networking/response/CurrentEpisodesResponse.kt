package se.jarbrant.androidarchsample.networking.response

import se.jarbrant.androidarchsample.models.CurrentEpisode

/**
 * @author Simon Jarbrant
 * Created on 2017-07-17.
 */
data class CurrentEpisodesResponse(val episodes: List<CurrentEpisode>)
