package com.example.codechallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.databinding.CurrencyItemBinding
import com.example.codechallenge.model.CurrencyInfo

class CurrencyListAdapter(
    private val itemClickListener: (CurrencyInfo) -> Unit
) : RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    var data = listOf<CurrencyInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount() : Int {
        return data.count()
    }

    inner class ViewHolder(binding: CurrencyItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val name = binding.currencyName
        private val sectionLayout = binding.root

        fun onBind(currencyInfo: CurrencyInfo) {
            name.text = currencyInfo.name
            sectionLayout.setOnClickListener {
                itemClickListener(currencyInfo)
            }
        }
    }
}