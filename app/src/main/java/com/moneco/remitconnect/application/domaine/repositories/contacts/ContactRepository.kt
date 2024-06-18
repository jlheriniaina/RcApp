package com.moneco.remitconnect.application.domaine.repositories.contacts

import android.provider.ContactsContract
import com.moneco.remitconnect.application.domaine.entites.Contact

interface ContactRepository {
    /**
     * Fetch all the contacts
     */
    suspend fun getContacts(): List<Contact>

    /**
     * Read the details of a contact
     *
     * @param contactId the contact id
     */
    suspend fun getContact(name: String): List<Contact>
}