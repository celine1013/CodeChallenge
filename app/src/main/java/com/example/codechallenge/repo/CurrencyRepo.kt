package com.example.codechallenge.repo

import android.content.Context

class CurrencyRepo(context: Context) {

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