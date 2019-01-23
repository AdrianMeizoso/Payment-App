package com.adrian.payment.contacts.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adrian.payment.contacts.domain.model.GameInfo
import com.adrian.payment.contacts.usecase.GetGames
import io.reactivex.disposables.CompositeDisposable

class GamesPagingDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                                   private val getGames: GetGames)
    : DataSource.Factory<Int, GameInfo>() {

    private val usersDataSourceLiveData = MutableLiveData<GamesPagingDataSource>()

    override fun create(): DataSource<Int, GameInfo> {
        val gamesPagingDataSource = GamesPagingDataSource(getGames, compositeDisposable)
        usersDataSourceLiveData.postValue(gamesPagingDataSource)
        return gamesPagingDataSource
    }
}