package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.domain.model.Contact
import com.adrian.payment.contacts.repository.ContactsRepository
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetDeviceContactsTest {

    @Mock
    lateinit var contactsRepositoryMock: ContactsRepository

    @InjectMocks
    internal lateinit var getDeviceContacts: GetDeviceContacts

    @Before
    fun injectMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getDeviceContactsAllList() {
        val getDeviceContactsResponse = listOf(
                Contact("aname1", "111111111", "https://urlbuena1"),
                Contact("aname2", "222222222", "https://urlbuena2"),
                Contact("aname3", "333333333", "https://urlbuena3"),
                Contact("aname4", "444444444", "https://urlbuena4"),
                Contact("aname5", "555555555", "https://urlbuena5"),
                Contact("aname6", "666666666", "https://urlbuena6")
        )
        `when`(contactsRepositoryMock.getDeviceContacts())
                .thenReturn(Single.just(getDeviceContactsResponse))

        val result = getDeviceContacts.execute(0, 6)

        val testObserver = TestObserver<List<Contact>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(6))
        assertThat(listResult[0].name, `is`("aname1"))
        assertThat(listResult[0].phone, `is`("111111111"))
        assertThat(listResult[0].avatarUrl, `is`("https://urlbuena1"))
    }

    @Test
    fun getDeviceContactsIndexThree() {
        val getDeviceContactsResponse = listOf(
                Contact("aname1", "111111111", "https://urlbuena1"),
                Contact("aname2", "222222222", "https://urlbuena2"),
                Contact("aname3", "333333333", "https://urlbuena3"),
                Contact("aname4", "444444444", "https://urlbuena4"),
                Contact("aname5", "555555555", "https://urlbuena5"),
                Contact("aname6", "666666666", "https://urlbuena6")
        )
        `when`(contactsRepositoryMock.getDeviceContacts())
                .thenReturn(Single.just(getDeviceContactsResponse))

        val result = getDeviceContacts.execute(3, 3)

        val testObserver = TestObserver<List<Contact>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(3))
        assertThat(listResult[0].name, `is`("aname4"))
        assertThat(listResult[0].phone, `is`("444444444"))
        assertThat(listResult[0].avatarUrl, `is`("https://urlbuena4"))
    }

    @Test
    fun getDeviceContactsIndexOutOfBounds() {
        val getDeviceContactsResponse = listOf(
                Contact("aname1", "111111111", "https://urlbuena1"),
                Contact("aname2", "222222222", "https://urlbuena2"),
                Contact("aname3", "333333333", "https://urlbuena3"),
                Contact("aname4", "444444444", "https://urlbuena4"),
                Contact("aname5", "555555555", "https://urlbuena5"),
                Contact("aname6", "666666666", "https://urlbuena6")
        )
        `when`(contactsRepositoryMock.getDeviceContacts())
                .thenReturn(Single.just(getDeviceContactsResponse))

        val result = getDeviceContacts.execute(1000, 6)

        val testObserver = TestObserver<List<Contact>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(0))
    }
}