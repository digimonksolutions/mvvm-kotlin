package com.cryptocapital.model

data class CoinDetailResponse(
    val additional_notices: List<Any>,
    val asset_platform_id: Any,
    val block_time_in_minutes: Double,
    val categories: List<String>,
    val coingecko_rank: Double,
    val coingecko_score: Double,
    val community_score: Double,
    val country_origin: String,
    val description: Description,
    val detail_platforms: DetailPlatforms,
    val developer_score: Double,
    val genesis_date: String,
    val hashing_algorithm: String,
    val id: String,
    val image: Image,
    val last_updated: String,
    val links: Links,
    val liquidity_score: Double,
    val market_cap_rank: Double,
    val market_data: MarketData,
    val name: String,
    val platforms: Platforms,
    val public_Doubleerest_score: Double,
    val public_Doubleerest_stats: PublicDoubleerestStats,
    val public_notice: Any,
    val sentiment_votes_down_percentage: Double,
    val sentiment_votes_up_percentage: Double,
    val status_updates: List<Any>,
    val symbol: String,
    val watchlist_portfolio_users: Double
) {
    data class Description(
        val en: String
    )

    data class DetailPlatforms(
        val dataX: X
    ) {
        data class X(
            val contract_address: String,
            val decimal_place: Any
        )
    }

    data class Image(
        val large: String,
        val small: String,
        val thumb: String
    )

    data class Links(
        val announcement_url: List<String>,
        val bitcoDoublealk_thread_identifier: Any,
        val blockchain_site: List<String>,
        val chat_url: List<String>,
        val facebook_username: String,
        val homepage: List<String>,
        val official_forum_url: List<String>,
        val repos_url: ReposUrl,
        val subreddit_url: String,
        val telegram_channel_identifier: String,
        val twitter_screen_name: String
    ) {
        data class ReposUrl(
            val bitbucket: List<Any>,
            val github: List<String>
        )
    }

    data class MarketData(
        val ath: Ath,
        val ath_change_percentage: AthChangePercentage,
        val ath_date: AthDate,
        val atl: Atl,
        val atl_change_percentage: AtlChangePercentage,
        val atl_date: AtlDate,
        val circulating_supply: Double,
        val current_price: CurrentPrice,
        val fdv_to_tvl_ratio: Any,
        val fully_diluted_valuation: FullyDilutedValuation,
        val high_24h: High24h,
        val last_updated: String,
        val low_24h: Low24h,
        val market_cap: MarketCap,
        val market_cap_change_24h: Double,
        val market_cap_change_24h_in_currency: MarketCapChange24hInCurrency,
        val market_cap_change_percentage_24h: Double,
        val market_cap_change_percentage_24h_in_currency: MarketCapChangePercentage24hInCurrency,
        val market_cap_rank: Double,
        val max_supply: Double,
        val mcap_to_tvl_ratio: Any,
        val price_change_24h: Double,
        val price_change_24h_in_currency: PriceChange24hInCurrency,
        val price_change_percentage_14d: Double,
        val price_change_percentage_14d_in_currency: PriceChangePercentage14dInCurrency,
        val price_change_percentage_1h_in_currency: PriceChangePercentage1hInCurrency,
        val price_change_percentage_1y: Double,
        val price_change_percentage_1y_in_currency: PriceChangePercentage1yInCurrency,
        val price_change_percentage_200d: Double,
        val price_change_percentage_200d_in_currency: PriceChangePercentage200dInCurrency,
        val price_change_percentage_24h: Double,
        val price_change_percentage_24h_in_currency: PriceChangePercentage24hInCurrency,
        val price_change_percentage_30d: Double,
        val price_change_percentage_30d_in_currency: PriceChangePercentage30dInCurrency,
        val price_change_percentage_60d: Double,
        val price_change_percentage_60d_in_currency: PriceChangePercentage60dInCurrency,
        val price_change_percentage_7d: Double,
        val price_change_percentage_7d_in_currency: PriceChangePercentage7dInCurrency,
        val roi: Any,
        val total_supply: Double,
        val total_value_locked: Any,
        val total_volume: TotalVolume
    ) {
        data class Ath(
            val usd: Double,
        )

        data class AthChangePercentage(
            val usd: Double,
        )

        data class AthDate(
            val usd: String,
        )

        data class Atl(
            val usd: Double,
        )

        data class AtlChangePercentage(
            val usd: Double,
        )

        data class AtlDate(
            val usd: String,
        )

        data class CurrentPrice(
            val usd: Double,
        )

        data class FullyDilutedValuation(
            val usd: Double,
        )

        data class High24h(
            val usd: Double,
        )

        data class Low24h(
            val usd: Double,
        )

        data class MarketCap(
            val usd: Double,
        )

        data class MarketCapChange24hInCurrency(
            val usd: Double,
        )

        data class MarketCapChangePercentage24hInCurrency(
            val usd: Double,
        )

        data class PriceChange24hInCurrency(
            val usd: Double,
        )

        data class PriceChangePercentage14dInCurrency(
            val usd: Double,
        )

        data class PriceChangePercentage1hInCurrency(
            val usd: Double,
        )

        data class PriceChangePercentage1yInCurrency(
            val usd: Double,
        )

        data class PriceChangePercentage200dInCurrency(
            val usd: Double,
        )

        data class PriceChangePercentage24hInCurrency(
            val usd: Double,
        )

        data class PriceChangePercentage30dInCurrency(
            val usd: Double,
        )

        data class PriceChangePercentage60dInCurrency(val usd: Double,
        )

        data class PriceChangePercentage7dInCurrency(
            val usd: Double,
        )

        data class TotalVolume(
            val usd: Double,
        )
    }

    data class Platforms(
        val platform: String
    )

    data class PublicDoubleerestStats(
        val alexa_rank: Double,
        val bing_matches: Any
    )
}