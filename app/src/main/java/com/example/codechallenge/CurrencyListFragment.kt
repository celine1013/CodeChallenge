package com.example.codechallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codechallenge.adapter.CurrencyListAdapter
import com.example.codechallenge.databinding.FragmentCurrencyListBinding
import com.example.codechallenge.repo.Resource

class CurrencyListFragment : Fragment() {

    private var _binding: FragmentCurrencyListBinding? = null
    private val binding get() = _binding!!

    private lateinit var currencyListAdapter: CurrencyListAdapter
    private lateinit var viewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[CurrencyViewModel::class.java]
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layout = LinearLayoutManager(requireActivity())
        binding.currencyListRecycler.layoutManager = layout
        binding.currencyListRecycler.addItemDecoration(
            DividerItemDecoration(requireActivity(), layout.orientation)
        )
        currencyListAdapter = CurrencyListAdapter { currencyInfo ->

        }
        binding.currencyListRecycler.adapter = currencyListAdapter

        viewModel.currencyListLiveData.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Success ->{
                    currencyListAdapter.data = it.data
                    binding.progressBar.visibility = View.GONE
                    binding.errorMsg.visibility = View.GONE
                }
                is Resource.Failure ->{
                    binding.progressBar.visibility = View.GONE
                    binding.errorMsg.text = it.throwable.localizedMessage ?: getString(R.string.load_currency_error)
                    binding.errorMsg.visibility = View.GONE
                }
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorMsg.visibility = View.GONE
                }
            }
        }

    }

}