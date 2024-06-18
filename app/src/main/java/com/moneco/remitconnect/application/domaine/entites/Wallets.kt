package com.moneco.remitconnect.application.domaine.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.moneco.remitconnect.R

/**
 * Wallet entity
 *
 * @property id
 * @property name
 */
@Entity(tableName = "wallets")
data class Wallets(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id : String= "",
    @SerializedName("name")
    var name : String = "",
){

    /**
     * logo from name
     *
     * @return
     */
    fun logo() : Int{
        return when(name){
            "Orange Money" -> R.drawable.orange_money_logo
            "Wave" -> R.drawable.wave_logo
            "CashPlus" -> R.drawable.cash_plus_logo
            "Moov Togo" -> R.drawable.mtn_logo
            else -> R.drawable.mtn_logo
        }
    }
}

