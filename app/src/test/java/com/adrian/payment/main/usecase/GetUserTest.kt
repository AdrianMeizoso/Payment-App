package com.adrian.payment.main.usecase

import com.adrian.payment.main.datasource.RunsDataSource
import com.adrian.payment.main.domain.model.UserData
import com.adrian.payment.main.domain.model.UserNames
import com.adrian.payment.main.domain.model.UserResponse
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

class GetUserTest {

    @Mock
    lateinit var runsDataSourceMock: RunsDataSource

    @InjectMocks
    internal lateinit var getUser: GetUser

    @Before
    fun injectMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getUser() {
        val getUserResponse = UserResponse(
            UserData("id1",
                UserNames("nameTest")
            ))
        `when`(runsDataSourceMock.getUser("id1")).thenReturn(Single.just(getUserResponse))

        val result = getUser.execute("id1")

        val testObserver = TestObserver<UserData>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val resultObserver = testObserver.values()[0]
        assertThat(resultObserver.id, `is`("id1"))
        assertThat(resultObserver.names.international, `is`("nameTest"))
    }

    @Test
    fun getUserWithOneFieldNull() {
        val getUserResponse = UserResponse(
            UserData("id1",
                UserNames(null)
            ))
        `when`(runsDataSourceMock.getUser("id1")).thenReturn(Single.just(getUserResponse))

        val result = getUser.execute("id1")

        val testObserver = TestObserver<UserData>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val resultObserver = testObserver.values()[0]
        assertThat(resultObserver.id, `is`("id1"))
        assertNull(resultObserver.names.international)
    }
}