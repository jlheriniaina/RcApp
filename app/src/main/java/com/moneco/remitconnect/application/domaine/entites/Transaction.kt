package com.moneco.remitconnect.application.domaine.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Transaction entity class
 *
 * @property id
 * @property country
 * @property phoneNumber
 * @property wallet
 * @property userId
 * @property firstName
 * @property lastName
 * @property amount
 */
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val country: String,
    val phoneNumber: String,
    val wallet: String,
    var userId : Long,
    val firstName: String,
    val lastName: String,
    var amount: Double = 0.0
)
