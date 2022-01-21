package com.example.codechallenge.repo

import android.content.Context

import com.example.codechallenge.model.SortingOrder
import com.example.codechallenge.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class CurrencyRepo(context: Context) {
    private val database: AppDatabase = AppDatabase.getInstance(context)

    fun getCurrencyListFlow(sort: SortingOrder) = flow {
        emit(Resource.Loading())
        emitAll(
            when(sort){
                SortingOrder.ASC -> database.currencyDao().getAllCurrencyInfoAsc()
                SortingOrder.DESC->database.currencyDao().getAllCurrencyInfoDesc()
                else -> database.currencyDao().getAllCurrencyInfo()
            }.map { list ->
                Resource.Success(list)
            }
        )
    }.flowOn(Dispatchers.IO).distinctUntilChanged()

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