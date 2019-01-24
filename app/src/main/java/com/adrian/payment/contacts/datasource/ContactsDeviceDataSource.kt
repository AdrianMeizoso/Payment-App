package com.adrian.payment.contacts.datasource

import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import com.adrian.payment.contacts.domain.model.Contact
import io.reactivex.Single


class ContactsDeviceDataSource(private val context: Context) : ContactsDataSource {

   override fun getDeviceContacts(): Single<List<Contact>> {
        val contactsList: ArrayList<Contact> = ArrayList()
        val resolver: ContentResolver = context.contentResolver
        val cursorQuery = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null,
                null)

        cursorQuery?.let {cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phoneNumber = (cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))).toInt()
                val displayPhotoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)) ?: ""

                if (phoneNumber > 0) {
                    val cursorPhone = context.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id.toString()), null)
                    cursorPhone?.let {
                        if (cursorPhone.count > 0) {
                            while (cursorPhone.moveToNext()) {
                                val phoneNumValue = cursorPhone.getString(
                                        cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                                    contactsList.add(Contact(
                                            name,
                                            phoneNumValue,
                                            displayPhotoUri
                                    ))
                            }
                        } else {
                                contactsList.add(Contact(
                                        name,
                                        null,
                                        displayPhotoUri
                                ))
                        }
                        cursorPhone.close()
                    }
                }
            }
            cursorQuery.close()
        }
        return Single.just(contactsList.sortedBy { contact -> contact.name })
    }
}