package com.moneco.remitconnect.application.domaine.entites


/**
 * Contact wrapper class
 *
 * @property id
 * @property name
 * @property phone
 * @property hasPhoneNumber
 */
data class Contact(
    val id: String,
    val name: String,
    val phone : String? = null,
    val hasPhoneNumber: Boolean = false
)