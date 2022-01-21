package com.example.codechallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codechallenge.databinding.ActivityDemoBinding
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider


class DemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoBinding
    private lateinit var viewModel:CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
        binding.fragmentContainerView.visibility = View.VISIBLE

        binding.sortBtn.setOnClickListener {
            viewModel.sortCurrencyList()
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
            //todo: refresh the list
        }
        return super.onOptionsItemSelected(item)
    }
}