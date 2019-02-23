package se.jarbrant.androidarchsample.views.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author Simon Jarbrant
 * Created on 2017-07-18.
 */
abstract class BindableViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: T)

    open fun unbind() {}
}
