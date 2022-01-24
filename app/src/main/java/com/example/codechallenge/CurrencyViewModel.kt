package com.example.codechallenge

import android.app.Application
import androidx.lifecycle.*
import com.example.codechallenge.model.CurrencyInfo
import com.example.codechallenge.model.SortingOrder
import com.example.codechallenge.repo.CurrencyRepo
import com.example.codechallenge.repo.Resource
import kotlinx.coroutines.launch

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {
    private val currencyRepo: CurrencyRepo = CurrencyRepo.getInstance(application.applicationContext)

    private val sortingStatusLiveData = MutableLiveData(SortingOrder.UNSORTED)
    fun getSortingStatusLiveData(): LiveData<SortingOrder> = sortingStatusLiveData

    private val currencyListLiveData: MutableLiveData<Resource<List<CurrencyInfo>>> = MutableLiveData(Resource.Idle())
    fun getCurrencyListLiveData(): LiveData<Resource<List<CurrencyInfo>>> = currencyListLiveData

    fun sortCurrencyList() = viewModelScope.launch{
        if (currencyListLiveData.value is Resource.Loading) return@launch

        currencyListLiveData.postValue(Resource.Loading())
        val newOrder = when(sortingStatusLiveData.value) {
            SortingOrder.UNSORTED -> SortingOrder.ASC
            SortingOrder.ASC -> SortingOrder.DESC
            else -> SortingOrder.UNSORTED //DESC
        }
        sortingStatusLiveData.postValue(newOrder)
        currencyListLiveData.postValue(currencyRepo.getCurrencyList(newOrder))
    }

    fun refreshCurrencyList() = viewModelScope.launch {
        if (currencyListLiveData.value is Resource.Loading) return@launch

        currencyListLiveData.postValue(Resource.Loading())
        currencyListLiveData.postValue(currencyRepo.getCurrencyList())
    }

    init {
        refreshCurrencyList() //initialize currencyList
    }
}