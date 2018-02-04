package se.jarbrant.androidarchsample.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
import se.jarbrant.androidarchsample.R

/**
 * @author Simon Jarbrant
 * Created on 2018-02-04.
 */
abstract class ToolbarFragment : Fragment() {

    protected abstract val toolbarTitle: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar? = view.findViewById(R.id.toolbar)
        if (toolbar != null) {
            val titleTextView: TextView = toolbar.findViewById(R.id.toolbar_title)
            titleTextView.text = toolbarTitle
        } else {
            Log.w(TAG, "No toolbar found! Layout is probably missing" +
                    " <include layout=\"@layout/toolbar\" />")
        }
    }

    companion object {
        private val TAG: String = ToolbarFragment::class.java.simpleName
    }
}
