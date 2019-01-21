package com.adrian.payment.main.usecase

import com.adrian.payment.main.datasource.RunsDataSource
import com.adrian.payment.main.domain.model.*
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

class GetSpeedRunTest {

    @Mock
    lateinit var runsDataSourceMock: RunsDataSource

    @InjectMocks
    internal lateinit var getSpeedRun: GetSpeedRun

    @Before
    fun injectMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getSpeedRun() {
        val getSpeedResponse = RunsResponse(
            listOf(
                RunData(
                    "id1",
                    RunVideos(listOf(RunLink("https://www.google.com"))),
                    RunTimes("RT0S"),
                    listOf(RunPlayer("playerTest"))
                ), RunData(
                    "id2",
                    RunVideos(listOf(RunLink("https://www.yahoo.com"))),
                    RunTimes("RT1S"),
                    listOf(RunPlayer("playerTest2"))
                )
            )
        )
        `when`(runsDataSourceMock.getSpeedruns("id1")).thenReturn(Single.just(getSpeedResponse))

        val result = getSpeedRun.execute("id1")

        val testObserver = TestObserver<RunData>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val resultObserver = testObserver.values()[0]
        assertThat(resultObserver.id, `is`("id1"))
        assertThat(resultObserver.videos!!.links!![0].uri, `is`("https://www.google.com"))
        assertThat(resultObserver.times!!.primary, `is`("RT0S"))
        assertThat(resultObserver.players!![0].id, `is`("playerTest"))
    }

    @Test
    fun getSpeedRunWithOneFieldNull() {
        val getSpeedResponse = RunsResponse(
            listOf(
                RunData(
                    "id1",
                    null,
                    RunTimes("RT0S"),
                    listOf(RunPlayer("playerTest"))
                ), RunData(
                    "id2",
                    RunVideos(listOf(RunLink("https://www.yahoo.com"))),
                    RunTimes("RT1S"),
                    listOf(RunPlayer("playerTest2"))
                )
            )
        )
        `when`(runsDataSourceMock.getSpeedruns("id1")).thenReturn(Single.just(getSpeedResponse))

        val result = getSpeedRun.execute("id1")

        val testObserver = TestObserver<RunData>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val resultObserver = testObserver.values()[0]
        assertThat(resultObserver.id, `is`("id1"))
        assertNull(resultObserver.videos)
        assertThat(resultObserver.times!!.primary, `is`("RT0S"))
        assertThat(resultObserver.players!![0].id, `is`("playerTest"))
    }
}