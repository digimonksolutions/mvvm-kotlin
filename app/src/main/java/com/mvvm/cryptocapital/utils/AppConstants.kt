package com.mvvm.cryptocapital.utils

class AppConstants {
    companion object {

        /**
         *  API ENDPOINTS
         * */
        const val BASE_URL = "https://api.coingecko.com/api/v3/coins/"
        const val COIN_LIST_URL = "markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en"
        const val COIN_DETAIL_URL = "?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false"
        const val COIN_CHART_DATA = "/market_chart?vs_currency=usd&days=30&interval=daily"



    }


}