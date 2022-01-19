package com.example.codechallenge.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codechallenge.model.CurrencyInfo

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency WHERE currency.id = :id")
    suspend fun getCurrency(id: String): CurrencyInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(prawn: CurrencyInfo): Long

    @Query("DELETE FROM currency WHERE currency.id = :id")
    suspend fun delete(id: String): Int

    @Query("DELETE FROM currency")
    suspend fun nukeTable(): Int
}