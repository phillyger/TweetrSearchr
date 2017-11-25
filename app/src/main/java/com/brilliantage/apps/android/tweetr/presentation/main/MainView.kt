package com.brilliantage.apps.android.tweetr.presentation.main

import android.support.v7.widget.RecyclerView
import com.brilliantage.apps.android.tweetr.model.search.SearchMetadata
import com.brilliantage.apps.android.tweetr.model.search.Status
import com.brilliantage.apps.android.tweetr.presentation.base.BaseView

/**
 * Created by gosullivan on 11/22/17.
 */
interface MainView : BaseView {

//    var progressVisible: Boolean

//    var query: String

//    var statusList: List<Status>

//    var meataData: SearchMetadata

//    var searchResults: RecyclerView


//    var searchButtonClickedCallback: ()->Unit

//    fun updateUI(statusList: List<Status>, metadata: SearchMetadata)
    fun updateUI()

}