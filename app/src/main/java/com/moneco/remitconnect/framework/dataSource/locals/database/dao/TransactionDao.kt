package com.moneco.remitconnect.framework.dataSource.locals.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moneco.remitconnect.application.domaine.entites.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction) : Long

    @Query("SELECT * FROM transactions WHERE userId = :id")
    suspend fun find(id : Long) : List<Transaction>

    @Query("SELECT * FROM transactions")
    suspend fun findAll() : List<Transaction>

    @Query("SELECT * FROM transactions WHERE firstName LIKE '%' || :term || '%' OR lastName LIKE '%' || :term || '%' AND userId =:id")
   suspend fun find(id: Long,term: String): List<Transaction>

    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun delete(id : Long)

}