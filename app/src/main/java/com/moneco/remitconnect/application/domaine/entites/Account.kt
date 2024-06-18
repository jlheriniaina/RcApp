package com.moneco.remitconnect.application.domaine.entites
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Account Entity
 *
 * @property id
 * @property name
 * @property country
 * @property mobileWallet
 */
@Entity(tableName = "account")
data class Account(
    @PrimaryKey
    @SerializedName("id")
    var id : String = "",
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("country")
    var country : String? = null,
    @SerializedName("mobile_wallet" )
    var mobileWallet : String? = null
)
