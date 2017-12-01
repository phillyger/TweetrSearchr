package com.brilliantage.apps.android.tweetr.presentation.main

import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.brilliantage.apps.android.tweetr.model.search.SearchMetadata
import com.brilliantage.apps.android.tweetr.model.search.Status
import com.brilliantage.apps.android.tweetr.presentation.base.BaseView

/**
 * Created by gosullivan on 11/22/17.
 */
interface MainView : BaseView {

    var query:String

    var emptyResults:Boolean

    var progressBar:Boolean

    fun updateUI(positionStart:Int, itemCount:Int)

}