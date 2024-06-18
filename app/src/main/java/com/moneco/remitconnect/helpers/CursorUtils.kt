package com.moneco.remitconnect.helpers

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull

/**
 * Extension function on Cursor to map each row of the cursor to an object of type T using the provided block.
 *
 * @receiver The Cursor object to map.
 * @param block Function that takes a Cursor as input and returns an object of type T.
 * @return A list of objects of type T mapped from the Cursor, or null if the Cursor is null or empty.
 */
fun <T> Cursor?.map(block: (Cursor) -> T): List<T>? {
    return this?.let {
        val result = mutableListOf<T>()
        if(moveToFirst()){
            do {
                result.add(block(this))
            }while (moveToNext())
        }
        result
    }
}

/**
 * Retrieves the value of the specified column as a nullable String from the Cursor.
 *
 * @receiver The Cursor object to retrieve the value from.
 * @param columnName The name of the column.
 * @return The value of the column as a String, or null if the column does not exist or the value is null.
 */
fun Cursor.optStringByName(columnName: String): String? {
    val index = getColumnIndex(columnName)
    return getStringOrNull(index)
}

/**
 * Retrieves the value of the specified column as an integer from the Cursor, with a default value if the column value is null.
 *
 * @receiver The Cursor object to retrieve the value from.
 * @param columnName The name of the column.
 * @param defValue The default value to return if the column value is null.
 * @return The value of the column as an integer, or the default value if the column does not exist or the value is null.
 */
fun Cursor.optIntByName(columnName: String, defValue: Int = -1): Int {
    val index = getColumnIndex(columnName)
    return getIntOrNull(index) ?: defValue
}

/**
 * Retrieves the value of the specified column as a non-null String from the Cursor.
 * Throws an exception if the column value is null.
 *
 * @receiver The Cursor object to retrieve the value from.
 * @param columnName The name of the column.
 * @return The value of the column as a String.
 * @throws IllegalArgumentException If the column does not exist or the value is null.
 */
fun Cursor.getStringByName(columnName: String): String {
    return getString(getColumnIndexOrThrow(columnName))
}

/**
 * Retrieves the value of the specified column as an integer from the Cursor.
 * Throws an exception if the column value is null.
 *
 * @receiver The Cursor object to retrieve the value from.
 * @param columnName The name of the column.
 * @return The value of the column as an integer.
 * @throws IllegalArgumentException If the column does not exist or the value is null.
 */
fun Cursor.getIntByName(columnName: String): Int {
    return getInt(getColumnIndexOrThrow(columnName))
}

/**
 * Retrieves the phone number associated with the specified contact ID from the ContentResolver.
 *
 * @receiver The ContentResolver object to perform the query.
 * @param contactId The ID of the contact.
 * @return The phone number associated with the contact ID, or null if not found.
 */
fun ContentResolver.getPhoneNumber(contactId: String): String? {
    val phoneProjection = arrayOf(
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    val phoneCursor: Cursor? = query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        phoneProjection,
        "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
        arrayOf(contactId),
        null
    )

    phoneCursor?.use {
        if (it.moveToFirst()) {
            val numberIndex = it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
            return it.getString(numberIndex)
        }
    }

    return null
}