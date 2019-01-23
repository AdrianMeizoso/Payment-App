package com.adrian.payment.contacts.domain

import androidx.paging.PageKeyedDataSource
import com.adrian.payment.contacts.domain.model.GameInfo
import com.adrian.payment.contacts.usecase.GetGames
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GamesPagingDataSource(
        private val getGames: GetGames,
        private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, GameInfo>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GameInfo>) {

        compositeDisposable.add(getGames.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gameInfoList: List<GameInfo>?, _: Throwable? ->
                    gameInfoList?.let { callback.onResult(it, 0, 20) }
                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GameInfo>) {

        getGames.offset = params.key
        compositeDisposable.add(getGames.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { gameInfoList: List<GameInfo>?, _: Throwable? ->
                    gameInfoList?.let { callback.onResult(it, params.key + 20) }
                })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GameInfo>) {}
}