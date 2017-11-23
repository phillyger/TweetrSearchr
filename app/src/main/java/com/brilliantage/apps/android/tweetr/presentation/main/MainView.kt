package com.brilliantage.apps.android.tweetr.presentation.main

import com.brilliantage.apps.android.tweetr.model.search.SearchMetadata
import com.brilliantage.apps.android.tweetr.model.search.Statuses
import com.brilliantage.apps.android.tweetr.presentation.base.BaseView

/**
 * Created by gosullivan on 11/22/17.
 */
interface MainView : BaseView {

//    var progressVisible: Boolean

//    var query: String

    var searchButtonClickedCallback: ()->Unit

    fun display(tweets: List<Statuses>, metadata: SearchMetadata)

}