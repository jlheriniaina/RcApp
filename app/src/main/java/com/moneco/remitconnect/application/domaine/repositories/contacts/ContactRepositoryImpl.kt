package com.moneco.remitconnect.application.domaine.repositories.contacts

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import com.moneco.remitconnect.application.domaine.entites.Contact
import com.moneco.remitconnect.application.domaine.entites.mapper.asContact
import com.moneco.remitconnect.helpers.map
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.util.Collections
import javax.inject.Inject

class ContactRepositoryImpl
@Inject constructor(
    @ApplicationContext
    private val ctx: Context,
) : ContactRepository {

    private val contentResolver: ContentResolver = ctx.contentResolver
    override suspend fun getContacts(): List<Contact> {
       return fetchContacts() ?: emptyList()
    }

    override suspend fun getContact(name: String): List<Contact> {
       return  fetchContactData(name) ?: Collections.emptyList()
    }

    private fun query(
        uri: Uri,
        projection: Array<String>,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
        sort: String? = null
    ): Cursor? {
        return contentResolver.query(uri, projection, selection, selectionArgs, sort)
    }


    private fun fetchContacts(): List<Contact>? {
        val cursor = query(
            uri = ContactsContract.Contacts.CONTENT_URI,
            projection = CONTACTS_PROJECTION,
            sort = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
        )
        val result: List<Contact>? = cursor.map { it.asContact(contentResolver) }
        cursor?.close()
        return result
    }


    private fun fetchContactData(contactId: String): List<Contact>? {
        val cursor: Cursor? = query(
            uri = ContactsContract.Data.CONTENT_URI,
            projection = CONTACT_DATA_PROJECTION,
            selection = CONTACT_DETAILS_SELECT,
            selectionArgs = arrayOf(
                contactId,
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE,
                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE,
                ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE
            )
        )
        val result: List<Contact>? = cursor.map { it.asContact(contentResolver) }
        cursor?.close()
        return result
    }

    companion object {

        @JvmStatic
        private val CONTACTS_PROJECTION = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
        )

        @JvmStatic
        private val CONTACT_DATA_PROJECTION = arrayOf(
            ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME_PRIMARY,
            ContactsContract.Data.CONTACT_ID,
            ContactsContract.Data.ACCOUNT_TYPE_AND_DATA_SET,
            ContactsContract.Data.MIMETYPE,
            ContactsContract.Data.DATA1,
            ContactsContract.Data.DATA2,
            ContactsContract.Data.DATA3,
        )

        private const val CONTACT_DETAILS_SELECT = "${ContactsContract.Data.CONTACT_ID}=?" +
                " AND ${ContactsContract.Data.MIMETYPE} IN (?,?,?,?)"

    }

}