package com.mvvm.cryptocapital.ui.fragment.coinDetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.cryptocapital.base.BaseViewModel
import com.mvvm.cryptocapital.data.Resource
import com.mvvm.cryptocapital.data.remote.Response
import com.mvvm.cryptocapital.data.repository.RemoteRepository
import com.mvvm.cryptocapital.model.CoinChartDataResponse
import com.mvvm.cryptocapital.model.CoinDetailResponse
import com.mvvm.cryptocapital.utils.AppConstants
import com.mvvm.cryptocapital.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailViewModel(
    private val remoteRepository: RemoteRepository
): BaseViewModel() {
    private val coinDetailLiveData = MutableLiveData<Event<Resource<CoinDetailResponse>>>()
    private val coinChartLiveData = MutableLiveData<Event<Resource<CoinChartDataResponse>>>()

    fun getCoinDetail(coinId:String): MutableLiveData<Event<Resource<CoinDetailResponse>>> {
        viewModelScope.launch {
            coinDetailLiveData.value = Event(Resource.loading())
            val response = remoteRepository.get(CoinDetailResponse::class.java, coinId + AppConstants.COIN_DETAIL_URL)
            withContext(Dispatchers.Main){
                when(response.status){
                    Response.Status.SUCCESS -> {
                        if (response.data == null){
                            coinDetailLiveData.value = Event(Resource.empty("No Data Found."))
                        }else{
                            coinDetailLiveData.value = Event(Resource.success(response.data))
                        }
                    }
                    Response.Status.ERROR -> {
                        coinDetailLiveData.value = Event(Resource.error(response.message.toString()))
                    }
                }
            }
        }
        return coinDetailLiveData
    }

    fun getCoinChartData(coinId:String): MutableLiveData<Event<Resource<CoinChartDataResponse>>> {
        viewModelScope.launch {
            coinChartLiveData.value = Event(Resource.loading())
            val response = remoteRepository.get(CoinChartDataResponse::class.java, coinId + AppConstants.COIN_CHART_DATA)
            withContext(Dispatchers.Main){
                when(response.status){
                    Response.Status.SUCCESS -> {
                        if (response.data == null){
                            coinChartLiveData.value = Event(Resource.empty(response.message))
                        }else{
                            coinChartLiveData.value = Event(Resource.success(response.data))
                        }
                    }
                    Response.Status.ERROR -> {
                        if (response.message.isNullOrEmpty()){
                            coinChartLiveData.value = Event(Resource.error("No Data Found."))
                        }else{
                            coinChartLiveData.value = Event(Resource.error(response.message.toString()))
                        }
                    }
                }
            }
        }
        return coinChartLiveData
    }

}