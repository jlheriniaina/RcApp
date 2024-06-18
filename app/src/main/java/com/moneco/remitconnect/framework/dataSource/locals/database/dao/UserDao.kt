package com.moneco.remitconnect.framework.dataSource.locals.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moneco.remitconnect.application.domaine.entites.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE id=:id")
    suspend fun find(id : Long) : User
}