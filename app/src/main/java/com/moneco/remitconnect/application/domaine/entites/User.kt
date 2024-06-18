package com.moneco.remitconnect.application.domaine.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * User Entity
 *
 * @property id
 * @property name
 * @property email
 * @property balance
 */
@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name : String,
    val email : String,
    val balance : Double
)