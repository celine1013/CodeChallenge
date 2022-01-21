package com.example.codechallenge

import android.app.Application
import androidx.lifecycle.*
import com.example.codechallenge.model.CurrencyInfo
import com.example.codechallenge.model.SortingOrder
import com.example.codechallenge.repo.CurrencyRepo
import com.example.codechallenge.repo.Resource
import kotlinx.coroutines.flow.map

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {
    private val currencyRepo: CurrencyRepo = CurrencyRepo.getInstance(application.applicationContext)
    private val sortStatusLiveData = MutableLiveData<SortingOrder>(SortingOrder.UNSORTED)

    val currencyListLiveData: LiveData<Resource<List<CurrencyInfo>>> = Transformations.switchMap(sortStatusLiveData) { sortingOrder->
        liveData {
            emitSource(
                currencyRepo.getCurrencyListFlow(sortingOrder).asLiveData(viewModelScope.coroutineContext)
            )
        }
    }
}