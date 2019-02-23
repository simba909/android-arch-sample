package se.jarbrant.androidarchsample.utils.extensions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity

/**
 * @author Simon Jarbrant
 * Created on 2017-11-07.
 */

fun <V : ViewModel> AppCompatActivity.getViewModel(clazz: Class<V>): V {
    return ViewModelProviders.of(this).get(clazz)
}
