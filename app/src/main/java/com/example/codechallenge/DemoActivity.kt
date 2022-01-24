package com.example.codechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codechallenge.databinding.ActivityDemoBinding
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.codechallenge.model.SortingOrder
import com.example.codechallenge.repo.Resource


class DemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoBinding
    private lateinit var viewModel:CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Latest Currency Information"

        viewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
        binding.fragmentContainerView.visibility = View.VISIBLE

        binding.sortBtn.setOnClickListener {
            viewModel.sortCurrencyList()
        }

        viewModel.getSortingStatusLiveData().observe(this) {
           binding.sortBtn.setImageResource(when (it) {
                SortingOrder.DESC -> R.drawable.ic_sort_desc
                SortingOrder.ASC -> R.drawable.ic_sort_asc
                else -> R.drawable.ic_sort_unordered
            })
        }

        viewModel.getCurrencyListLiveData().observe(this) {
            binding.sortBtn.isEnabled = it !is Resource.Loading
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.currency_list_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_refresh) {
            viewModel.refreshCurrencyList()
        }
        return super.onOptionsItemSelected(item)
    }
}