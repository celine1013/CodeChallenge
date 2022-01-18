package com.example.codechallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.codechallenge.repo.CurrencyRepo

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {
    private val currencyRepo: CurrencyRepo = CurrencyRepo.getInstance(application.applicationContext)

}