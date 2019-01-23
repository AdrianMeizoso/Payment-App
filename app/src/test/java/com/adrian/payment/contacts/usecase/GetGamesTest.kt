package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.datasource.RunsDataSource
import com.adrian.payment.contacts.domain.model.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetGamesTest {

    @Mock
    lateinit var runsDataSourceMock: RunsDataSource

    @InjectMocks
    internal lateinit var getGames: GetGames

    @Before
    fun injectMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getGames() {
        val getGamesResponse = GamesResponse(listOf(
            GameInfo("id1",
                GameNames("nameTest", "",""),
                GameAssets(GameAsset("https://www.google.com")))
        ))
        `when`(runsDataSourceMock.getGames(0)).thenReturn(Single.just(getGamesResponse))

        val result = getGames.execute()

        val testObserver = TestObserver<List<GameInfo>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(1))
        assertThat(listResult[0].id, `is`("id1"))
        assertThat(listResult[0].names.international, `is`("nameTest"))
        assertThat(listResult[0].assets.coverLarge.uri, `is`("https://www.google.com"))
    }

    @Test
    fun getGamesWithOneFieldNull() {
        val getGamesResponse = GamesResponse(listOf(
            GameInfo("id1",
                GameNames(null, "",""),
                GameAssets(GameAsset("https://www.google.com")))
        ))
        `when`(runsDataSourceMock.getGames(0)).thenReturn(Single.just(getGamesResponse))

        val result = getGames.execute()

        val testObserver = TestObserver<List<GameInfo>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(1))
        assertThat(listResult[0].id, `is`("id1"))
        assertNull(listResult[0].names.international)
        assertThat(listResult[0].assets.coverLarge.uri, `is`("https://www.google.com"))
    }
}