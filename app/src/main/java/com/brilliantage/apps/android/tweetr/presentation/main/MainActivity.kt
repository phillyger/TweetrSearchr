package com.brilliantage.apps.android.tweetr.presentation.main

import android.os.Bundle

import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.brilliantage.apps.android.tweetr.R
import com.brilliantage.apps.android.tweetr.presentation.base.PresenterBaseActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by gosullivan on 11/22/17.
 */
class MainActivity : PresenterBaseActivity(), MainView {

    private val TAG:String = MainActivity::class.java.simpleName

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    val mainPresenter by presenter { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initView()
    }

    /*
        * Initialize the recycler view
        * */
    private fun initView() {
        linearLayoutManager = LinearLayoutManager(this)

        searchAdapter = SearchAdapter(mainPresenter as MainPresenter)
        searchResultsView.adapter = searchAdapter
        searchResultsView.layoutManager = linearLayoutManager

        setRecyclerViewScrollListener(linearLayoutManager)
    }

    override fun onStart() {
        super.onStart()
        shouldShowEmptyView()
    }
    /*
     * Initialize the toolbar
     * */
    private fun initToolbar() {
        val toolbar = toolbar as Toolbar
        setSupportActionBar(toolbar)
    }


    /*
    * Update the UI with the new result set
    * */
    override fun updateUI(positionStart:Int, itemCount:Int) {

        // determine if
        shouldShowEmptyView()

        // hide progress
        progressView.visibility = View.INVISIBLE

        // Update the search adapter dataset on the main UI thread
        runOnUiThread {
            searchAdapter.notifyItemRangeInserted(positionStart,itemCount)


        }
    }

    /*
    *  Sets a listener on the recyclerview scroll event
    * */
    private fun setRecyclerViewScrollListener(layoutMgr:LinearLayoutManager) {

        searchResultsView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutMgr) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                loadMoreTweets(page)
            }
        })
    }


    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    fun loadMoreTweets(offset: Int) {
        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`

        (mainPresenter as MainPresenter).loadMoreTweets()
    }

    /*
     * Add in the Search menu option.
    * */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)

        val search:MenuItem? = menu?.findItem(R.id.search) as? MenuItem

        search?.let {
            val searchView:SearchView? = MenuItemCompat.getActionView(it) as? SearchView
            searchView?.let {
                it.setQuery("", false)
                search(it)
            }

        }

        return true
    }

    /*
     *  Perform the search
    * */
    private fun search(searchView: SearchView) {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                progressView.visibility = View.VISIBLE
                searchResultsView.visibility = View.INVISIBLE

                Log.d(TAG,"Search Text: $query")
                (mainPresenter as MainPresenter).resetSearch()
                (mainPresenter as MainPresenter).searchTweets(query, null, null)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.d(TAG,"Search Text: $newText")
                return true
            }


        })

    }

    /*
    *  Determine if we should show empty.
    * */
    fun shouldShowEmptyView() {

        if ((mainPresenter as MainPresenter).getStatusListCount() == 0)
        {
            emptyView.visibility = View.VISIBLE
            searchResultsView.visibility = View.INVISIBLE
        } else {
            emptyView.visibility = View.INVISIBLE
            searchResultsView.visibility = View.VISIBLE
        }


    }
}