package com.example.codechallenge.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codechallenge.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency WHERE currency.id = :id")
    suspend fun getCurrency(id: String): CurrencyInfo?

    @Query("SELECT * FROM currency")
    fun getAllCurrencyInfo(): List<CurrencyInfo>

    @Query("SELECT * FROM currency ORDER BY currency.name ASC")
    fun getAllCurrencyInfoAsc(): List<CurrencyInfo>

    @Query("SELECT * FROM currency ORDER BY currency.name DESC")
    fun getAllCurrencyInfoDesc(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(prawn: CurrencyInfo): Long

    @Query("DELETE FROM currency WHERE currency.id = :id")
    suspend fun delete(id: String): Int

    @Query("DELETE FROM currency")
    suspend fun nukeTable(): Int
}