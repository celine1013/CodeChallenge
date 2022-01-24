package com.example.codechallenge.repo

import android.content.Context
import com.example.codechallenge.model.CurrencyInfo

import com.example.codechallenge.model.SortingOrder
import com.example.codechallenge.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class CurrencyRepo(context: Context) {
    private val database: AppDatabase = AppDatabase.getInstance(context)

    //assuming that the dataset is large, it is more efficient to sort in database level than in Kotlin runtime
    suspend fun getCurrencyList(sort: SortingOrder = SortingOrder.UNSORTED): Resource<List<CurrencyInfo>> = withContext(Dispatchers.IO){
        val currencyList = when(sort){
            SortingOrder.ASC -> database.currencyDao().getAllCurrencyInfoAsc()
            SortingOrder.DESC->database.currencyDao().getAllCurrencyInfoDesc()
            else -> database.currencyDao().getAllCurrencyInfo()
        }
        return@withContext if (currencyList.isEmpty())
            Resource.Failure(Exception("No existing currency data"))
        else
            Resource.Success(currencyList)
    }

    companion object{
        // For Singleton instantiation
        @Volatile
        private var instance: CurrencyRepo? = null

        @JvmStatic
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CurrencyRepo(context.applicationContext).also { instance = it }
            }
    }
}