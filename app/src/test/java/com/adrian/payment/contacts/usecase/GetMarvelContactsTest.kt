package com.adrian.payment.contacts.usecase

import com.adrian.payment.contacts.domain.model.*
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

class GetMarvelContactsTest {

    @Mock
    lateinit var contactsRepositoryMock: ContactsRepository

    @InjectMocks
    internal lateinit var getMarvelContactsMock: GetMarvelContacts

    @Before
    fun injectMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getMarvelContacts() {
        val getDeviceContactsResponse = MarvelResponse(100, "Ok",
                MarvelData(0, 20, listOf(
                        MarvelHero(1, "name1", "El cascabel",
                                MarvelThumbnail("path1", "jpg")),
                        MarvelHero(2, "name2", "El arbol",
                                MarvelThumbnail("path2", "png")),
                        MarvelHero(3, "name3", "La casa",
                                MarvelThumbnail("path3", "tiff"))

                )))
        `when`(contactsRepositoryMock.getMarvelContacts(0, 6))
                .thenReturn(Single.just(getDeviceContactsResponse))

        val result = getMarvelContactsMock.execute(0, 6)

        val testObserver = TestObserver<List<Contact>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(3))
        assertThat(listResult[0].name, `is`("name1"))
        assertNull(listResult[0].phone)
        assertThat(listResult[0].avatarUrl, `is`("path1/standard_fantastic.jpg"))
    }
}