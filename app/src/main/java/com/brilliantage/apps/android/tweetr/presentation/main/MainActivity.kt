package com.brilliantage.apps.android.tweetr.presentation.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.brilliantage.apps.android.tweetr.R
import com.brilliantage.apps.android.tweetr.model.search.SearchMetadata
import com.brilliantage.apps.android.tweetr.model.search.Status
import com.brilliantage.apps.android.tweetr.presentation.base.PresenterBaseActivity
import com.marcinmoskala.kotlinandroidviewbindings.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by gosullivan on 11/22/17.
 */
class MainActivity : PresenterBaseActivity(), MainView {

    val TAG:String = "MainActivity"

//    private var searchStatusList:ArrayList<Status> = ArrayList<Status>()
//    private var searchMetaData:SearchMetadata? = null
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()


//    override var progressVisible by bindToLoading(R.id.progressView, R.id.searchResultsView)

//    override var query: String by bindToEditText(R.id.searchInputView)

//    override var searchButtonClickedCallback by bindToClick(R.id.searchButton)

    val mainPresenter by presenter { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)

        searchAdapter = SearchAdapter(mainPresenter as MainPresenter)
        searchResults.adapter = searchAdapter
        searchResults.layoutManager = linearLayoutManager

        setRecyclerViewScrollListener()
    }

    override fun updateUI() {
        runOnUiThread {
             searchAdapter.notifyDataSetChanged()
        }
    }

    private fun setRecyclerViewScrollListener() {
        searchResults.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = searchResults!!.layoutManager.itemCount
                if (!(mainPresenter as MainPresenter).isLoadingData  && totalItemCount == lastVisibleItemPosition + 1) {
                    (mainPresenter as MainPresenter).loadMoreTweets()
                }
            }
        })
    }
}