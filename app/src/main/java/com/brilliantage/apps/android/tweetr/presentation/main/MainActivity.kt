package com.brilliantage.apps.android.tweetr.presentation.main

import android.os.Bundle
import com.brilliantage.apps.android.tweetr.R
import com.brilliantage.apps.android.tweetr.model.search.SearchMetadata
import com.brilliantage.apps.android.tweetr.model.search.Statuses
import com.brilliantage.apps.android.tweetr.presentation.base.PresenterBaseActivity
import com.marcinmoskala.kotlinandroidviewbindings.*

/**
 * Created by gosullivan on 11/22/17.
 */
class MainActivity : PresenterBaseActivity(), MainView {


//    override var progressVisible by bindToLoading(R.id.progressView, R.id.searchResultsView)

//    override var query: String by bindToEditText(R.id.searchInputView)

    override var searchButtonClickedCallback by bindToClick(R.id.searchButton)

    val mainPresenter by presenter { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun display(tweets: List<Statuses>, metadata: SearchMetadata) {

    }


}