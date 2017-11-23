package com.brilliantage.apps.android.tweetr.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by gosullivan on 11/22/17.
 */
open class RxPresenter : Presenter {

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        add(disposable)
    }

    var subscriptions = CompositeDisposable()

    override fun onDestroy() {
        subscriptions.dispose()
    }

}