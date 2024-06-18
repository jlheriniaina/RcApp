package com.moneco.remitconnect.application.domaine.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.moneco.remitconnect.R

/**
 * Country entity
 *
 * @property id
 * @property name
 * @property currencyCode
 */
@Entity(tableName = "country")
data class Country(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id : String = "",
    @SerializedName("name")
    var name : String = "",
    @SerializedName("currency_code" )
    var currencyCode : String = ""
){

    /**
     * Returns the flag resource ID for the given country name.
     *
     * @return The flag resource ID for the country.
     */
    fun getFlags(): Int {
        return when (name) {
            "Benin" -> R.drawable.flag_benin
            "Morocco" -> R.drawable.flag_morocco
            "Senegal" -> R.drawable.flag_senegal
            "Togo" -> R.drawable.flag_togo
            else -> R.drawable.flag_benin
        }
    }

    /**
     * Returns a list of countries.
     *
     * @return  countries code.
     */
    fun getCountryCode(): String {
        return when(name){
            "Benin" -> "+229"
            "Morocco" -> "+212"
            "Senegal" -> "+228"
            "Togo" -> "+221"
            else -> "+261"
        }
    }
}

val defaultCountry = Country(
    id = "1",
    name = "Benin",
    currencyCode = "XOF"
)