package se.jarbrant.androidarchsample.extensions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment

/**
 * @author Simon Jarbrant
 * Created on 2018-02-03.
 */

fun <V : ViewModel> Fragment.getViewModel(clazz: Class<V>): V {
    return ViewModelProviders.of(this).get(clazz)
}
