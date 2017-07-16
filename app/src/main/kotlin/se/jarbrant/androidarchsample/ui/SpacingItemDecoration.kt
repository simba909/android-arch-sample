package se.jarbrant.androidarchsample.ui

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @author Simon Jarbrant
 * Created on 2017-07-16.
 */
class SpacingItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect,
                                view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {

        val itemPosition = parent.getChildAdapterPosition(view)
        val adapterSize = parent.adapter.itemCount

        if (itemPosition != adapterSize - 1) {
            outRect.bottom = 40
        }
    }
}
