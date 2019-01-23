package com.adrian.payment.contacts.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adrian.payment.common.BaseViewModel
import com.adrian.payment.contacts.domain.GamesPagingDataSourceFactory
import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.domain.model.GameInfo
import com.adrian.payment.contacts.domain.model.RunData
import com.adrian.payment.contacts.domain.model.UserData
import com.adrian.payment.contacts.usecase.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(getGames: GetGames,
                    private val getSpeedRun: GetSpeedRun,
                    private val getUser: GetUser,
                    getDeviceContacts: GetDeviceContacts,
                    getMarvelContacts: GetMarvelContacts) : BaseViewModel() {

    val gamesList: LiveData<PagedList<GameInfo>>
    var runData: MutableLiveData<RunData>? = null
    var userData: MutableLiveData<UserData>? = null

    val contactsData: MutableLiveData<List<Contact>> = MutableLiveData()

    var position: Int = 0

    private val pagedListConfig by lazy {
        PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(40)
                .setPageSize(20)
                .setPrefetchDistance(40)
                .build()
    }

    init {
        val sourceFactory = GamesPagingDataSourceFactory(disposables, getGames)
        gamesList = LivePagedListBuilder(sourceFactory, pagedListConfig).build()

        /*disposables.add(getDeviceContacts.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contacts, _: Throwable? ->
                    Log.v("CERDO", "Pintamos contactos: $contacts")
                    contactsData.value = contacts
                })*/

        disposables.add(getMarvelContacts.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { contacts, _: Throwable? ->
                    Log.v("CERDO", "Pintamos contactos: $contacts")
                    contactsData.value = contacts
                })
    }

    fun getGameByPos(gameId: Int): GameInfo? {
        position = gameId
        return gamesList.value?.get(gameId)
    }

    fun getSpeedRunByGameId(gameId: String) {
        disposables.add(getSpeedRun.execute(gameId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { runDataInfo, _: Throwable? ->
                runData?.value = runDataInfo ?: return@subscribe
        })
    }

    fun getUserById(userId: String) {
        disposables.add(getUser.execute(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userDataInfo, _: Throwable? ->
                userData?.value = userDataInfo ?: return@subscribe
            })
    }

    fun reset() {
        runData = null
        userData = null
    }
}