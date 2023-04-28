package com.cryptocapital.ui.fragment.coinList.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cryptocapital.base.BaseViewModel
import com.cryptocapital.data.Resource
import com.cryptocapital.data.remote.Response
import com.cryptocapital.data.repository.RemoteRepository
import com.cryptocapital.model.CoinListResponse
import com.cryptocapital.ui.fragment.coinList.CoinListFragmentDirections
import com.cryptocapital.utils.AppConstants
import com.cryptocapital.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val SAVED_DATA = "SAVED_DATA"
class CoinListViewModel(
    private val remoteRepository: RemoteRepository,
    private val handle:SavedStateHandle
): BaseViewModel() {
     val test = "test"
    val coinListLiveData = MutableLiveData<Event<Resource<CoinListResponse>>>()

    fun getCoinList(): MutableLiveData<Event<Resource<CoinListResponse>>> {
        viewModelScope.launch {
            coinListLiveData.value = Event(Resource.loading())
            val response = remoteRepository.get(CoinListResponse::class.java,AppConstants.COIN_LIST_URL)
            withContext(Dispatchers.Main){
                when(response.status){
                    Response.Status.SUCCESS -> {
                        if (response.data.isNullOrEmpty()){
                            coinListLiveData.value = Event(Resource.empty("No Data Found."))
                        }else{
                            coinListLiveData.value = Event(Resource.success(response.data))
                        }
                    }
                    Response.Status.ERROR -> {
                        coinListLiveData.value = Event(Resource.error(response.message.toString()))
                    }
                }
            }
        }
        return coinListLiveData
    }

    fun navigateToCoinDetailPage(coinID:String,color:Int){
        navigate(CoinListFragmentDirections.actionCoinListFragmentToCoinDetailFragment(coinID,color))
    }

    fun saveData(data: CoinListResponse){
        handle[SAVED_DATA] = data
    }

    fun getSavedData(): MutableLiveData<CoinListResponse> {
        return handle.getLiveData<CoinListResponse>(SAVED_DATA)
    }



}