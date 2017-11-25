package com.brilliantage.apps.android.tweetr.presentation.main

import android.content.ContentValues.TAG
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.brilliantage.apps.android.tweetr.R
import com.brilliantage.apps.android.tweetr.model.search.Status
import com.brilliantage.apps.android.tweetr.utils.inflate
import com.brilliantage.apps.android.tweetr.utils.loadUrl
import kotlinx.android.synthetic.main.search_recycler_view_row_item.view.*

/**
 * Created by gosullivan on 11/24/17.
 */
class SearchAdapter(private val mainPresenter: MainPresenter) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val TAG:String = "SearchAdapter"

    override fun getItemCount() = mainPresenter.getStatusListCount()

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        val statusList = mainPresenter.getStatusList()
        val itemStatus = statusList[position]
        holder.bindStatus(itemStatus)  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchViewHolder {
        val inflatedView = parent.inflate(R.layout.search_recycler_view_row_item, false)
        return SearchViewHolder(inflatedView)
    }

    // View Holder
    class SearchViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val TAG:String = "SearchViewHolder"

        private var view: View = v
        private var status: Status? = null

        fun bindStatus(status: Status) {
            this.status = status
            Log.d(TAG, "status.user.profileImageUrl: " + status.user.profileImageUrl  )
            view.userProfileImageView.loadUrl(status.user.profileImageUrl)

            Log.d(TAG,"status.user.name: " + status.user.name)

            view.userNameView.text = status.user.name

            Log.d(TAG,"status.user.screenName: " + status.user.screenName)
            view.userScreenNameView.text = status.user.screenName

            view.mainTextView.text = status.text
        }

        companion object {
            //5
            private val STATUS_KEY = "STATUS"
        }
    }
}