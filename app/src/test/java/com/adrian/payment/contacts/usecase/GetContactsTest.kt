package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.repository.ContactsRepository
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

class GetContactsTest {

    @Mock
    lateinit var getMarvelContacts: GetMarvelContacts
    @Mock
    lateinit var getDeviceContacts: GetDeviceContacts
    @InjectMocks
    internal lateinit var getContacts: GetContacts

    @Before
    fun injectMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getDeviceContactsAllList() {
        val getMarvelContactsResponse = listOf(
                Contact("marvelname1", null, "https://urlbuena1"),
                Contact("marvelname2", null, "https://urlbuena2"),
                Contact("marvelname3", null, "https://urlbuena3"),
                Contact("marvelname4", null, "https://urlbuena4"),
                Contact("marvelname5", null, "https://urlbuena5"),
                Contact("marvelname6", null, "https://urlbuena6")
        )
        `when`(getMarvelContacts.execute(0, 6))
                .thenReturn(Single.just(getMarvelContactsResponse))

        val getDeviceContactsResponse = listOf(
                Contact("aname1", "111111111", "https://urlbuena1"),
                Contact("aname2", "222222222", "https://urlbuena2"),
                Contact("aname3", "333333333", "https://urlbuena3"),
                Contact("aname4", "444444444", "https://urlbuena4"),
                Contact("aname5", "555555555", "https://urlbuena5"),
                Contact("aname6", "666666666", "https://urlbuena6")
        )
        `when`(getDeviceContacts.execute(0, 6))
                .thenReturn(Single.just(getDeviceContactsResponse))

        val result = getContacts.execute( 6)

        val testObserver = TestObserver<List<Contact>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(12))
        assertThat(listResult[0].name, `is`("aname1"))
        assertThat(listResult[0].phone, `is`("111111111"))
        assertThat(listResult[0].avatarUrl, `is`("https://urlbuena1"))
        assertThat(listResult[6].name, `is`("marvelname1"))
        assertNull(listResult[6].phone)
        assertThat(listResult[6].avatarUrl, `is`("https://urlbuena1"))
    }
}