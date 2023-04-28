package com.cryptocapital.ui.fragment.coinList

import android.os.Bundle
import com.cryptocapital.model.CoinListResponse
import com.cryptocapital.R
import com.cryptocapital.data.Resource
import com.cryptocapital.databinding.FragmentCoinListBinding
import com.cryptocapital.ui.fragment.coinList.adapter.CoinListAdapter
import com.cryptocapital.ui.fragment.coinList.viewmodel.CoinListViewModel
import com.cryptocapital.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.cryptocapital.extensions.toastMessage

class CoinListFragment : BaseFragment<FragmentCoinListBinding, CoinListViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_coin_list
    override val viewModel: CoinListViewModel by viewModel()
    private lateinit var adapter:CoinListAdapter

    override fun onReady(savedInstanceState: Bundle?) {
        if (viewModel.getSavedData().value != null){
            setupUi(viewModel.getSavedData().value!!)
        }else {
            getCoinListData()
        }

    }

    private fun getCoinListData() {
        viewModel.getCoinList().observe(viewLifecycleOwner){ data ->
            when(data.getContentIfNotHandled()?.status){
                Resource.Status.SUCCESS -> {
                    binding.loading = false
                    viewModel.saveData(data.peekContent().data!!)
                    setupUi(data.peekContent().data!!)
                }
                Resource.Status.ERROR -> {
                    binding.loading = false
                    requireContext().toastMessage(data.peekContent().message.toString())
                }
                Resource.Status.LOADING -> {
                    binding.loading = true
                }
                Resource.Status.EMPTY -> {
                    binding.loading = false
                    requireContext().toastMessage(data.peekContent().message.toString())
                }

                else -> {}
            }
        }

    }

    private fun setupUi(coinListData: ArrayList<CoinListResponse.CoinListResponseItem>) {
        adapter = CoinListAdapter(coinListData,viewModel)
        binding.rvCoinList.adapter = adapter

    }

    override fun onNetworkAvailable() {
    }

    override fun onNetworkLost() {

    }

}