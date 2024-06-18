package com.moneco.remitconnect.application.domaine.entites.mapper
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.*
import com.moneco.remitconnect.application.domaine.entites.Contact
import com.moneco.remitconnect.helpers.getIntByName
import com.moneco.remitconnect.helpers.getPhoneNumber
import com.moneco.remitconnect.helpers.getStringByName
import com.moneco.remitconnect.helpers.optStringByName


/**
 *
 *
 * @param contentResolver
 * @return
 */
fun Cursor.asContact(contentResolver : ContentResolver): Contact {
    val uuid = this.getStringByName(ContactsContract.Contacts._ID)
    return Contact(
        id = getStringByName(ContactsContract.Contacts._ID),
        name = getStringByName(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY),
        phone = contentResolver.getPhoneNumber(uuid),
        hasPhoneNumber = getIntByName(ContactsContract.Contacts.HAS_PHONE_NUMBER) == 1
    )
}
