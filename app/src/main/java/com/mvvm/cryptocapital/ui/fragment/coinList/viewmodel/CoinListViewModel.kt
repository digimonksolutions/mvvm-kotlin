package com.mvvm.cryptocapital.ui.fragment.coinList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mvvm.cryptocapital.base.BaseViewModel
import com.mvvm.cryptocapital.data.Resource
import com.mvvm.cryptocapital.data.remote.Response
import com.mvvm.cryptocapital.data.repository.RemoteRepository
import com.mvvm.cryptocapital.model.CoinListResponse
import com.mvvm.cryptocapital.ui.fragment.coinList.CoinListFragmentDirections
import com.mvvm.cryptocapital.utils.AppConstants
import com.mvvm.cryptocapital.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val SAVED_DATA = "SAVED_DATA"
class CoinListViewModel(
    private val remoteRepository: RemoteRepository,
    private val handle:SavedStateHandle
): BaseViewModel() {
    val coinListLiveData = MutableLiveData<Resource<CoinListResponse>>()


    fun getCoinList(){
        viewModelScope.launch {
            if (handle.get<CoinListResponse>(SAVED_DATA) == null) {

                coinListLiveData.value = Resource.loading()
                val response =
                    remoteRepository.get(CoinListResponse::class.java, AppConstants.COIN_LIST_URL)
                withContext(Dispatchers.Main) {
                    when (response.status) {
                        Response.Status.SUCCESS -> {
                            if (response.data.isNullOrEmpty()) {
                                coinListLiveData.value = Resource.empty("No Data Found.")
                            } else {
                                coinListLiveData.value = Resource.success(response.data)
                            }
                        }

                        Response.Status.ERROR -> {
                            coinListLiveData.value = Resource.error(response.message.toString())
                        }
                    }
                }
            }
        }
    }

    fun navigateToCoinDetailPage(coinID:String,color:Int){
        navigate(CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(coinID,color))
    }



}