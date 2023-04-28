package com.cryptocapital.model

data class CoinChartDataResponse(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
)