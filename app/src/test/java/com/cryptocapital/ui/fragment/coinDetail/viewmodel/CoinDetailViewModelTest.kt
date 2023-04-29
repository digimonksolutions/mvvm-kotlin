package com.mvvm.cryptocapital.ui.fragment.coinDetail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mvvm.cryptocapital.data.Resource
import com.mvvm.cryptocapital.data.remote.Response
import com.mvvm.cryptocapital.data.repository.RemoteRepository
import com.mvvm.cryptocapital.model.CoinChartDataResponse
import com.mvvm.cryptocapital.model.CoinDetailResponse
import com.mvvm.cryptocapital.utils.AppConstants
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class CoinDetailViewModelTest {
    private val coinDetailExpectedResponse =
        "{\"id\":\"bitcoin\",\"symbol\":\"btc\",\"name\":\"Bitcoin\",\"asset_platform_id\":null,\"platforms\":{\"\":\"\"},\"detail_platforms\":{\"\":{\"decimal_place\":null,\"contract_address\":\"\"}},\"block_time_in_minutes\":10,\"hashing_algorithm\":\"SHA-256\",\"categories\":[\"Cryptocurrency\",\"Layer 1 (L1)\"],\"public_notice\":null,\"additional_notices\":[],\"description\":{\"en\":\"Bitcoin is the first successful internet money based on peer-to-peer technology; whereby no central bank or authority is involved in the transaction and production of the Bitcoin currency. It was created by an anonymous individual/group under the name, Satoshi Nakamoto. The source code is available publicly as an open source project, anybody can look at it and be part of the developmental process.\\r\\n\\r\\nBitcoin is changing the way we see money as we speak. The idea was to produce a means of exchange, independent of any central authority, that could be transferred electronically in a secure, verifiable and immutable way. It is a decentralized peer-to-peer internet currency making mobile payment easy, very low transaction fees, protects your identity, and it works anywhere all the time with no central authority and banks.\\r\\n\\r\\nBitcoin is designed to have only 21 million BTC ever created, thus making it a deflationary currency. Bitcoin uses the \\u003ca href=\\\"https://www.coingecko.com/en?hashing_algorithm=SHA-256\\\"\\u003eSHA-256\\u003c/a\\u003e hashing algorithm with an average transaction confirmation time of 10 minutes. Miners today are mining Bitcoin using ASIC chip dedicated to only mining Bitcoin, and the hash rate has shot up to peta hashes.\\r\\n\\r\\nBeing the first successful online cryptography currency, Bitcoin has inspired other alternative currencies such as \\u003ca href=\\\"https://www.coingecko.com/en/coins/litecoin\\\"\\u003eLitecoin\\u003c/a\\u003e, \\u003ca href=\\\"https://www.coingecko.com/en/coins/peercoin\\\"\\u003ePeercoin\\u003c/a\\u003e, \\u003ca href=\\\"https://www.coingecko.com/en/coins/primecoin\\\"\\u003ePrimecoin\\u003c/a\\u003e, and so on.\\r\\n\\r\\nThe cryptocurrency then took off with the innovation of the turing-complete smart contract by \\u003ca href=\\\"https://www.coingecko.com/en/coins/ethereum\\\"\\u003eEthereum\\u003c/a\\u003e which led to the development of other amazing projects such as \\u003ca href=\\\"https://www.coingecko.com/en/coins/eos\\\"\\u003eEOS\\u003c/a\\u003e, \\u003ca href=\\\"https://www.coingecko.com/en/coins/tron\\\"\\u003eTron\\u003c/a\\u003e, and even crypto-collectibles such as \\u003ca href=\\\"https://www.coingecko.com/buzz/ethereum-still-king-dapps-cryptokitties-need-1-billion-on-eos\\\"\\u003eCryptoKitties\\u003c/a\\u003e.\"},\"links\":{\"homepage\":[\"http://www.bitcoin.org\",\"\",\"\"],\"blockchain_site\":[\"https://blockchair.com/bitcoin/\",\"https://btc.com/\",\"https://btc.tokenview.io/\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],\"official_forum_url\":[\"https://bitcointalk.org/\",\"\",\"\"],\"chat_url\":[\"\",\"\",\"\"],\"announcement_url\":[\"\",\"\"],\"twitter_screen_name\":\"bitcoin\",\"facebook_username\":\"bitcoins\",\"bitcointalk_thread_identifier\":null,\"telegram_channel_identifier\":\"\",\"subreddit_url\":\"https://www.reddit.com/r/Bitcoin/\",\"repos_url\":{\"github\":[\"https://github.com/bitcoin/bitcoin\",\"https://github.com/bitcoin/bips\"],\"bitbucket\":[]}},\"image\":{\"thumb\":\"https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png?1547033579\",\"small\":\"https://assets.coingecko.com/coins/images/1/small/bitcoin.png?1547033579\",\"large\":\"https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579\"},\"country_origin\":\"\",\"genesis_date\":\"2009-01-03\",\"sentiment_votes_up_percentage\":63.57,\"sentiment_votes_down_percentage\":36.43,\"watchlist_portfolio_users\":1277863,\"market_cap_rank\":1,\"coingecko_rank\":1,\"coingecko_score\":83.151,\"developer_score\":99.241,\"community_score\":83.341,\"liquidity_score\":100.011,\"public_interest_score\":0.073,\"market_data\":{\"current_price\":{\"aed\":103183,\"ars\":6122305,\"aud\":41964,\"bch\":228.74,\"bdt\":2981206,\"bhd\":10590.95,\"bmd\":28098,\"bnb\":85.883,\"brl\":141868,\"btc\":1.0,\"cad\":38024,\"chf\":25066,\"clp\":22280849,\"cny\":193539,\"czk\":602962,\"dkk\":190747,\"dot\":4642,\"eos\":26027,\"eth\":14.696192,\"eur\":25595,\"gbp\":22673,\"hkd\":220517,\"huf\":9669978,\"idr\":420350190,\"ils\":102812,\"inr\":2306352,\"jpy\":3759265,\"krw\":37320943,\"kwd\":8607.14,\"lkr\":9071422,\"ltc\":314.223,\"mmk\":58973889,\"mxn\":505901,\"myr\":124697,\"ngn\":12933980,\"nok\":298354,\"nzd\":45852,\"php\":1571977,\"pkr\":7961646,\"pln\":117985,\"rub\":2284359,\"sar\":105387,\"sek\":290031,\"sgd\":37494,\"thb\":965415,\"try\":545099,\"twd\":859885,\"uah\":1037162,\"usd\":28098,\"vef\":2813.41,\"vnd\":660188524,\"xag\":1115.88,\"xau\":14.14,\"xdr\":20825,\"xlm\":293813,\"xrp\":60054,\"yfi\":3.436737,\"zar\":508946,\"bits\":1000746,\"link\":3779,\"sats\":100074591},\"total_value_locked\":null,\"mcap_to_tvl_ratio\":null,\"fdv_to_tvl_ratio\":null,\"roi\":null,\"ath\":{\"aed\":253608,\"ars\":6913791,\"aud\":93482,\"bch\":237.769,\"bdt\":5922005,\"bhd\":26031,\"bmd\":69045,\"bnb\":143062,\"brl\":380542,\"btc\":1.003301,\"cad\":85656,\"chf\":62992,\"clp\":55165171,\"cny\":440948,\"czk\":1505245,\"dkk\":444134,\"dot\":5526,\"eos\":26798,\"eth\":624.203,\"eur\":59717,\"gbp\":51032,\"hkd\":537865,\"huf\":21673371,\"idr\":984115318,\"ils\":216131,\"inr\":5128383,\"jpy\":7828814,\"krw\":81339064,\"kwd\":20832,\"lkr\":14190616,\"ltc\":578.455,\"mmk\":126473151,\"mxn\":1409247,\"myr\":286777,\"ngn\":28379648,\"nok\":591777,\"nzd\":97030,\"php\":3454759,\"pkr\":11814869,\"pln\":275506,\"rub\":6075508,\"sar\":258938,\"sek\":596346,\"sgd\":91123,\"thb\":2258593,\"try\":850326,\"twd\":1914232,\"uah\":1815814,\"usd\":69045,\"vef\":8618768857,\"vnd\":1563347910,\"xag\":2815.08,\"xau\":37.72,\"xdr\":48913,\"xlm\":324982,\"xrp\":159288,\"yfi\":11.593182,\"zar\":1057029,\"bits\":1058236,\"link\":74906,\"sats\":105823579},\"ath_change_percentage\":{\"aed\":-59.39345,\"ars\":-11.61909,\"aud\":-55.1854,\"bch\":-4.10015,\"bdt\":-49.75703,\"bhd\":-59.39031,\"bmd\":-59.3846,\"bnb\":-99.94007,\"brl\":-62.78861,\"btc\":-0.32896,\"cad\":-55.6951,\"chf\":-60.25655,\"clp\":-59.70216,\"cny\":-56.19459,\"czk\":-60.01038,\"dkk\":-57.12416,\"dot\":-16.17475,\"eos\":-3.03501,\"eth\":-97.65426,\"eur\":-57.21234,\"gbp\":-55.64758,\"hkd\":-59.08145,\"huf\":-55.51671,\"idr\":-57.34897,\"ils\":-52.54672,\"inr\":-55.11654,\"jpy\":-52.0484,\"krw\":-54.19729,\"kwd\":-58.75177,\"lkr\":-36.1992,\"ltc\":-45.85125,\"mmk\":-53.46139,\"mxn\":-64.17597,\"myr\":-56.60259,\"ngn\":-54.51406,\"nok\":-49.62297,\"nzd\":-52.87312,\"php\":-54.58047,\"pkr\":-32.74478,\"pln\":-57.22992,\"rub\":-62.41275,\"sar\":-59.37606,\"sek\":-51.44945,\"sgd\":-58.92922,\"thb\":-57.33122,\"try\":-36.02274,\"twd\":-55.15965,\"uah\":-42.99309,\"usd\":-59.3846,\"vef\":-99.99997,\"vnd\":-57.85323,\"xag\":-60.35725,\"xau\":-62.58591,\"xdr\":-57.50786,\"xlm\":-9.74169,\"xrp\":-62.397,\"yfi\":-70.42326,\"zar\":-51.95087,\"bits\":-5.45134,\"link\":-94.97542,\"sats\":-5.45134},\"ath_date\":{\"aed\":\"2021-11-10T14:24:11.849Z\",\"ars\":\"2021-11-10T14:24:11.849Z\",\"aud\":\"2021-11-10T14:24:11.849Z\",\"bch\":\"2023-04-12T03:35:59.839Z\",\"bdt\":\"2021-11-10T14:24:11.849Z\",\"bhd\":\"2021-11-10T14:24:11.849Z\",\"bmd\":\"2021-11-10T14:24:11.849Z\",\"bnb\":\"2017-10-19T00:00:00.000Z\",\"brl\":\"2021-11-09T04:09:45.771Z\",\"btc\":\"2019-10-15T16:00:56.136Z\",\"cad\":\"2021-11-10T14:24:11.849Z\",\"chf\":\"2021-11-10T17:30:22.767Z\",\"clp\":\"2021-11-09T04:09:45.771Z\",\"cny\":\"2021-11-10T14:24:11.849Z\",\"czk\":\"2021-11-10T14:24:11.849Z\",\"dkk\":\"2021-11-10T14:24:11.849Z\",\"dot\":\"2020-12-27T11:42:47.567Z\",\"eos\":\"2022-06-13T09:14:54.961Z\",\"eth\":\"2015-10-20T00:00:00.000Z\",\"eur\":\"2021-11-10T14:24:11.849Z\",\"gbp\":\"2021-11-10T14:24:11.849Z\",\"hkd\":\"2021-11-10T14:24:11.849Z\",\"huf\":\"2021-11-10T16:54:53.781Z\",\"idr\":\"2021-11-10T14:24:11.849Z\",\"ils\":\"2021-10-20T14:54:17.702Z\",\"inr\":\"2021-11-10T14:24:11.849Z\",\"jpy\":\"2021-11-10T14:24:11.849Z\",\"krw\":\"2021-11-10T14:24:11.849Z\",\"kwd\":\"2021-11-10T14:24:11.849Z\",\"lkr\":\"2022-03-29T12:14:23.745Z\",\"ltc\":\"2022-06-13T07:48:18.897Z\",\"mmk\":\"2021-10-20T14:54:17.702Z\",\"mxn\":\"2021-11-10T17:30:22.767Z\",\"myr\":\"2021-11-10T14:24:11.849Z\",\"ngn\":\"2021-11-09T04:09:45.771Z\",\"nok\":\"2021-11-10T17:30:22.767Z\",\"nzd\":\"2021-11-10T14:24:11.849Z\",\"php\":\"2021-11-10T14:24:11.849Z\",\"pkr\":\"2021-11-10T14:24:11.849Z\",\"pln\":\"2021-11-10T14:24:11.849Z\",\"rub\":\"2022-03-07T16:43:46.826Z\",\"sar\":\"2021-11-10T14:24:11.849Z\",\"sek\":\"2021-11-10T17:30:22.767Z\",\"sgd\":\"2021-11-09T00:00:00.000Z\",\"thb\":\"2021-11-10T14:24:11.849Z\",\"try\":\"2021-12-20T16:44:25.022Z\",\"twd\":\"2021-11-10T14:24:11.849Z\",\"uah\":\"2021-04-14T06:52:46.198Z\",\"usd\":\"2021-11-10T14:24:11.849Z\",\"vef\":\"2021-01-03T12:04:17.372Z\",\"vnd\":\"2021-11-10T14:24:11.849Z\",\"xag\":\"2021-11-09T04:09:45.771Z\",\"xau\":\"2021-10-20T14:54:17.702Z\",\"xdr\":\"2021-11-10T14:24:11.849Z\",\"xlm\":\"2023-03-21T10:20:18.157Z\",\"xrp\":\"2021-01-03T07:54:40.240Z\",\"yfi\":\"2020-07-18T00:00:00.000Z\",\"zar\":\"2021-11-10T17:49:04.400Z\",\"bits\":\"2021-05-19T16:00:11.072Z\",\"link\":\"2017-12-12T00:00:00.000Z\",\"sats\":\"2021-05-19T16:00:11.072Z\"},\"atl\":{\"aed\":632.31,\"ars\":1478.98,\"aud\":72.61,\"bch\":3.513889,\"bdt\":9390.25,\"bhd\":45.91,\"bmd\":121.77,\"bnb\":52.17,\"brl\":149.66,\"btc\":0.99895134,\"cad\":69.81,\"chf\":63.26,\"clp\":107408,\"cny\":407.23,\"czk\":4101.56,\"dkk\":382.47,\"dot\":991.882,\"eos\":908.141,\"eth\":6.779735,\"eur\":51.3,\"gbp\":43.9,\"hkd\":514.37,\"huf\":46598,\"idr\":658780,\"ils\":672.18,\"inr\":3993.42,\"jpy\":6641.83,\"krw\":75594,\"kwd\":50.61,\"lkr\":22646,\"ltc\":20.707835,\"mmk\":117588,\"mxn\":859.32,\"myr\":211.18,\"ngn\":10932.64,\"nok\":1316.03,\"nzd\":84.85,\"php\":2880.5,\"pkr\":17315.84,\"pln\":220.11,\"rub\":2206.43,\"sar\":646.04,\"sek\":443.81,\"sgd\":84.47,\"thb\":5644.35,\"try\":392.91,\"twd\":1998.66,\"uah\":553.37,\"usd\":67.81,\"vef\":766.19,\"vnd\":3672339,\"xag\":3.37,\"xau\":0.0531,\"xdr\":44.39,\"xlm\":21608,\"xrp\":9908,\"yfi\":0.23958075,\"zar\":666.26,\"bits\":950993,\"link\":598.477,\"sats\":95099268},\"atl_change_percentage\":{\"aed\":16186.46435,\"ars\":413055.23255,\"aud\":57597.40364,\"bch\":6389.10343,\"bdt\":31585.94638,\"bhd\":22923.88757,\"bmd\":22929.32349,\"bnb\":64.33878,\"brl\":94518.7215,\"btc\":0.10498,\"cad\":54264.31598,\"chf\":39474.48687,\"clp\":20597.09182,\"cny\":47332.92131,\"czk\":14575.9271,\"dkk\":49688.84071,\"dot\":367.01235,\"eos\":2761.35023,\"eth\":115.96948,\"eur\":49709.40563,\"gbp\":51455.36306,\"hkd\":42687.1852,\"huf\":20589.769,\"idr\":63614.0316,\"ils\":15157.91532,\"inr\":57539.71522,\"jpy\":56421.17644,\"krw\":49183.802,\"kwd\":16877.90289,\"lkr\":39878.83764,\"ltc\":1412.59818,\"mmk\":49955.16677,\"mxn\":58649.93378,\"myr\":58833.77014,\"ngn\":117975.29103,\"nok\":22553.02934,\"nzd\":53788.90148,\"php\":54374.36106,\"pkr\":45789.27443,\"pln\":53433.81524,\"rub\":103398.09516,\"sar\":16182.41471,\"sek\":65137.84632,\"sgd\":44207.69981,\"thb\":16973.96324,\"try\":138358.22475,\"twd\":42846.28462,\"uah\":186961.68384,\"usd\":41255.58291,\"vef\":266.47935,\"vnd\":17842.26051,\"xag\":33017.96695,\"xau\":26474.1246,\"xdr\":46720.72485,\"xlm\":1257.45758,\"xrp\":504.53377,\"yfi\":1331.2023,\"zar\":76129.99228,\"bits\":5.21088,\"link\":528.88354,\"sats\":5.21088},\"atl_date\":{\"aed\":\"2015-01-14T00:00:00.000Z\",\"ars\":\"2015-01-14T00:00:00.000Z\",\"aud\":\"2013-07-05T00:00:00.000Z\",\"bch\":\"2017-08-02T00:00:00.000Z\",\"bdt\":\"2013-09-08T00:00:00.000Z\",\"bhd\":\"2013-09-08T00:00:00.000Z\",\"bmd\":\"2013-09-08T00:00:00.000Z\",\"bnb\":\"2022-11-27T02:35:06.345Z\",\"brl\":\"2013-07-05T00:00:00.000Z\",\"btc\":\"2019-10-21T00:00:00.000Z\",\"cad\":\"2013-07-05T00:00:00.000Z\",\"chf\":\"2013-07-05T00:00:00.000Z\",\"clp\":\"2015-01-14T00:00:00.000Z\",\"cny\":\"2013-07-05T00:00:00.000Z\",\"czk\":\"2015-01-14T00:00:00.000Z\",\"dkk\":\"2013-07-05T00:00:00.000Z\",\"dot\":\"2021-05-19T11:04:48.978Z\",\"eos\":\"2019-04-11T00:00:00.000Z\",\"eth\":\"2017-06-12T00:00:00.000Z\",\"eur\":\"2013-07-05T00:00:00.000Z\",\"gbp\":\"2013-07-05T00:00:00.000Z\",\"hkd\":\"2013-07-05T00:00:00.000Z\",\"huf\":\"2015-01-14T00:00:00.000Z\",\"idr\":\"2013-07-05T00:00:00.000Z\",\"ils\":\"2015-01-14T00:00:00.000Z\",\"inr\":\"2013-07-05T00:00:00.000Z\",\"jpy\":\"2013-07-05T00:00:00.000Z\",\"krw\":\"2013-07-05T00:00:00.000Z\",\"kwd\":\"2015-01-14T00:00:00.000Z\",\"lkr\":\"2015-01-14T00:00:00.000Z\",\"ltc\":\"2013-11-28T00:00:00.000Z\",\"mmk\":\"2013-09-08T00:00:00.000Z\",\"mxn\":\"2013-07-05T00:00:00.000Z\",\"myr\":\"2013-07-05T00:00:00.000Z\",\"ngn\":\"2013-07-06T00:00:00.000Z\",\"nok\":\"2015-01-14T00:00:00.000Z\",\"nzd\":\"2013-07-05T00:00:00.000Z\",\"php\":\"2013-07-05T00:00:00.000Z\",\"pkr\":\"2015-01-14T00:00:00.000Z\",\"pln\":\"2013-07-05T00:00:00.000Z\",\"rub\":\"2013-07-05T00:00:00.000Z\",\"sar\":\"2015-01-14T00:00:00.000Z\",\"sek\":\"2013-07-05T00:00:00.000Z\",\"sgd\":\"2013-07-05T00:00:00.000Z\",\"thb\":\"2015-01-14T00:00:00.000Z\",\"try\":\"2015-01-14T00:00:00.000Z\",\"twd\":\"2013-07-05T00:00:00.000Z\",\"uah\":\"2013-07-06T00:00:00.000Z\",\"usd\":\"2013-07-06T00:00:00.000Z\",\"vef\":\"2013-09-08T00:00:00.000Z\",\"vnd\":\"2015-01-14T00:00:00.000Z\",\"xag\":\"2013-07-05T00:00:00.000Z\",\"xau\":\"2013-07-05T00:00:00.000Z\",\"xdr\":\"2013-07-05T00:00:00.000Z\",\"xlm\":\"2018-11-20T00:00:00.000Z\",\"xrp\":\"2018-12-25T00:00:00.000Z\",\"yfi\":\"2020-09-12T20:09:36.122Z\",\"zar\":\"2013-07-05T00:00:00.000Z\",\"bits\":\"2021-05-19T13:14:13.071Z\",\"link\":\"2020-08-16T08:13:13.338Z\",\"sats\":\"2021-05-19T13:14:13.071Z\"},\"market_cap\":{\"aed\":1990520227608,\"ars\":118103224505902,\"aud\":809643546992,\"bch\":4420477548,\"bdt\":57511011497338,\"bhd\":204326036816,\"bmd\":542036387988,\"bnb\":1661740793,\"brl\":2737066944784,\"btc\":19352337,\"cad\":733410465313,\"chf\":483734954096,\"clp\":429542156024871,\"cny\":3733546640460,\"czk\":11631227665599,\"dkk\":3679042713503,\"dot\":89740501969,\"eos\":503405032630,\"eth\":283921588,\"eur\":493668854979,\"gbp\":437333387066,\"hkd\":4254060931627,\"huf\":186368913318259,\"idr\":8109470049309462,\"ils\":1982760976714,\"inr\":44492515413633,\"jpy\":72528262967494,\"krw\":719960041570941,\"kwd\":166086995753,\"lkr\":174998500011651,\"ltc\":6077454972,\"mmk\":1137676315990706,\"mxn\":9759148351167,\"myr\":2405557489890,\"ngn\":249511821106071,\"nok\":5757793456202,\"nzd\":884204446415,\"php\":30325310340794,\"pkr\":153589603153544,\"pln\":2275654675254,\"rub\":44139771141204,\"sar\":2033219686108,\"sek\":5598228784306,\"sgd\":723347559770,\"thb\":18627351491397,\"try\":10515072297854,\"twd\":16588211141823,\"uah\":20008095417501,\"usd\":542036387988,\"vef\":54274103529,\"vnd\":12735820199286938,\"xag\":21541436680,\"xau\":272725609,\"xdr\":401738941539,\"xlm\":5680642073864,\"xrp\":1162311559451,\"yfi\":66486280,\"zar\":9822789385517,\"bits\":19350892936287,\"link\":72987019706,\"sats\":1935089293628708},\"market_cap_rank\":1,\"fully_diluted_valuation\":{\"aed\":2159993636932,\"ars\":128158563724058,\"aud\":878576808932,\"bch\":4796838155,\"bdt\":62407513957829,\"bhd\":221722408675,\"bmd\":588185506885,\"bnb\":1803221836,\"brl\":2970101535564,\"btc\":21000000,\"cad\":795853222873,\"chf\":524920273764,\"clp\":466113486785720,\"cny\":4051421771421,\"czk\":12621513410891,\"dkk\":3992277365961,\"dot\":97381031622,\"eos\":546265067895,\"eth\":308094746,\"eur\":535699949549,\"gbp\":474568065262,\"hkd\":4616252784569,\"huf\":202236411017617,\"idr\":8799912436182706,\"ils\":2151573761401,\"inr\":48280619735296,\"jpy\":78703338119699,\"krw\":781257626558992,\"kwd\":180227685721,\"lkr\":189897917768002,\"ltc\":6594891067,\"mmk\":1234538373107332,\"mxn\":10590044777253,\"myr\":2610367279554,\"ngn\":270755322379281,\"nok\":6248013486962,\"nzd\":959485842702,\"php\":32907215141855,\"pkr\":166666261869273,\"pln\":2469404505530,\"rub\":47897842723868,\"sar\":2206328538422,\"sek\":6074863437445,\"sgd\":784933558937,\"thb\":20213289036841,\"try\":11410328285155,\"twd\":18000535748126,\"uah\":21711589859536,\"usd\":588185506885,\"vef\":58895014804,\"vnd\":13820151239874838,\"xag\":23375480195,\"xau\":295945538,\"xdr\":435943099396,\"xlm\":6164293415888,\"xrp\":1261271067596,\"yfi\":72146940,\"zar\":10659104225802,\"bits\":20998432988327,\"link\":79201153526,\"sats\":2099843298832738},\"total_volume\":{\"aed\":80389494302,\"ars\":4769870792259,\"aud\":32694332685,\"bch\":178210681,\"bdt\":2322649660601,\"bhd\":8251377514,\"bmd\":21890775346,\"bnb\":66911150,\"brl\":110528713798,\"btc\":779678,\"cad\":29624348460,\"chf\":19528979594,\"clp\":17358947033652,\"cny\":150785849659,\"czk\":469765969163,\"dkk\":148610453859,\"dot\":3616535989,\"eos\":20277559813,\"eth\":11449763,\"eur\":19940788859,\"gbp\":17664542257,\"hkd\":171804277607,\"huf\":7533852669052,\"idr\":327493660943406,\"ils\":80100711194,\"inr\":1796872465066,\"jpy\":2928832505830,\"krw\":29076642683612,\"kwd\":6705801212,\"lkr\":7067519698843,\"ltc\":244810502,\"mmk\":45946392532711,\"mxn\":394146431027,\"myr\":97151260984,\"ngn\":10076827576487,\"nok\":232447154227,\"nzd\":35722877673,\"php\":1224723252049,\"pkr\":6202896286270,\"pln\":91922123914,\"rub\":1779737832808,\"sar\":82106497266,\"sek\":225962012031,\"sgd\":29211773017,\"thb\":752152308387,\"try\":424685419862,\"twd\":669934365184,\"uah\":808050403233,\"usd\":21890775346,\"vef\":2191923335,\"vnd\":514351038056810,\"xag\":869380103,\"xau\":11012592,\"xdr\":16224698400,\"xlm\":228908567475,\"xrp\":46787991371,\"yfi\":2677552,\"zar\":396518230863,\"bits\":779678345355,\"link\":2943880049,\"sats\":77967834535537},\"high_24h\":{\"aed\":106230,\"ars\":6299867,\"aud\":42868,\"bch\":232.825,\"bdt\":3070602,\"bhd\":10905.1,\"bmd\":28926,\"bnb\":89.666,\"brl\":146403,\"btc\":1.0,\"cad\":38965,\"chf\":25831,\"clp\":22968053,\"cny\":198880,\"czk\":619030,\"dkk\":196372,\"dot\":4637,\"eos\":26656,\"eth\":14.744084,\"eur\":26350,\"gbp\":23225,\"hkd\":227058,\"huf\":9963673,\"idr\":432243278,\"ils\":105697,\"inr\":2374616,\"jpy\":3884184,\"krw\":38195378,\"kwd\":8862.93,\"lkr\":9313455,\"ltc\":323.809,\"mmk\":60740462,\"mxn\":523037,\"myr\":128359,\"ngn\":13321257,\"nok\":306120,\"nzd\":46734,\"php\":1617451,\"pkr\":8197657,\"pln\":121488,\"rub\":2359113,\"sar\":108487,\"sek\":298546,\"sgd\":38518,\"thb\":991422,\"try\":561178,\"twd\":883489,\"uah\":1068260,\"usd\":28926,\"vef\":2896.35,\"vnd\":679490865,\"xag\":1141.76,\"xau\":14.41,\"xdr\":21486,\"xlm\":299511,\"xrp\":60706,\"yfi\":3.483061,\"zar\":522619,\"bits\":1002617,\"link\":3788,\"sats\":100261741},\"low_24h\":{\"aed\":102527,\"ars\":6086130,\"aud\":41717,\"bch\":227.137,\"bdt\":2964232,\"bhd\":10527.35,\"bmd\":27920,\"bnb\":84.905,\"brl\":140977,\"btc\":1.0,\"cad\":37760,\"chf\":24971,\"clp\":22132676,\"cny\":192500,\"czk\":599066,\"dkk\":189984,\"dot\":4504,\"eos\":25915,\"eth\":14.496771,\"eur\":25493,\"gbp\":22514,\"hkd\":219077,\"huf\":9612546,\"idr\":417739790,\"ils\":102071,\"inr\":2294073,\"jpy\":3737964,\"krw\":37113891,\"kwd\":8553.55,\"lkr\":8991187,\"ltc\":309.705,\"mmk\":58640125,\"mxn\":503220,\"myr\":123907,\"ngn\":12860204,\"nok\":297517,\"nzd\":45479,\"php\":1562811,\"pkr\":7914187,\"pln\":117461,\"rub\":2277772,\"sar\":104712,\"sek\":288670,\"sgd\":37279,\"thb\":960084,\"try\":541737,\"twd\":855411,\"uah\":1031319,\"usd\":27920,\"vef\":2795.59,\"vnd\":655927755,\"xag\":1112.0,\"xau\":14.02,\"xdr\":20735,\"xlm\":291959,\"xrp\":58427,\"yfi\":3.382036,\"zar\":504636,\"bits\":998117,\"link\":3628,\"sats\":99811694},\"price_change_24h\":-538.9517107587,\"price_change_percentage_24h\":-1.88204,\"price_change_percentage_7d\":-7.58891,\"price_change_percentage_14d\":0.19482,\"price_change_percentage_30d\":-0.58952,\"price_change_percentage_60d\":15.70196,\"price_change_percentage_200d\":47.37682,\"price_change_percentage_1y\":-32.1268,\"market_cap_change_24h\":-13735066650.221,\"market_cap_change_percentage_24h\":-2.47135,\"price_change_24h_in_currency\":{\"aed\":-1984.919681233092,\"ars\":-105317.17739512213,\"aud\":-646.6900173106842,\"bch\":0.06155554,\"bdt\":-58678.75119436253,\"bhd\":-204.66696230395428,\"bmd\":-538.9517107587126,\"bnb\":-2.564135079310816,\"brl\":-3545.9542720734316,\"btc\":0.0,\"cad\":-584.0219830937276,\"chf\":-575.133069209096,\"clp\":-472050.9753501825,\"cny\":-3488.9880401142,\"czk\":-11149.280080784112,\"dkk\":-4063.2005810595,\"dot\":127.464,\"eos\":-6.1790564427064965,\"eth\":-0.04789124692404023,\"eur\":-546.6124605443147,\"gbp\":-366.94711507102693,\"hkd\":-4267.484852292604,\"huf\":-199829.71162006073,\"idr\":-7710353.950056255,\"ils\":-1847.6566238319647,\"inr\":-46825.20745818736,\"jpy\":-97107.69103764696,\"krw\":-621365.8673241735,\"kwd\":-169.3066532103294,\"lkr\":-148866.47951103747,\"ltc\":-3.7266252462729312,\"mmk\":-1158962.5139750615,\"mxn\":-12459.08151112945,\"myr\":-2377.5494078116462,\"ngn\":-254006.4701783955,\"nok\":-4912.3854849045165,\"nzd\":-586.1742122767391,\"php\":-35593.53738060617,\"pkr\":-154006.16094693542,\"pln\":-2519.852983124554,\"rub\":-51413.234018259216,\"sar\":-2019.0022999120847,\"sek\":-6011.956892408198,\"sgd\":-695.1975033603958,\"thb\":-18250.92478743114,\"try\":-10530.197422080208,\"twd\":-15943.929819830577,\"uah\":-20410.80633008154,\"usd\":-538.9517107587126,\"vef\":-53.965234798269194,\"vnd\":-12505135.559515953,\"xag\":-16.46865808973098,\"xau\":-0.17376610228557432,\"xdr\":-445.95847182947546,\"xlm\":-1228.0960990386084,\"xrp\":1428,\"yfi\":0.03310515,\"zar\":-10525.74466001871,\"bits\":922.09,\"link\":85.805,\"sats\":92209},\"price_change_percentage_1h_in_currency\":{\"aed\":0.35587,\"ars\":0.31673,\"aud\":0.31127,\"bch\":0.59432,\"bdt\":0.29097,\"bhd\":0.34043,\"bmd\":0.35587,\"bnb\":1.11411,\"brl\":0.35189,\"btc\":0.0,\"cad\":0.38072,\"chf\":0.20019,\"clp\":0.38752,\"cny\":0.34567,\"czk\":0.30511,\"dkk\":0.23664,\"dot\":0.43947,\"eos\":0.27816,\"eth\":0.42147,\"eur\":0.23725,\"gbp\":0.29037,\"hkd\":0.35203,\"huf\":0.31926,\"idr\":0.27655,\"ils\":0.26552,\"inr\":0.33239,\"jpy\":0.35887,\"krw\":0.25604,\"kwd\":0.34768,\"lkr\":0.6098,\"ltc\":0.75667,\"mmk\":0.2875,\"mxn\":0.37949,\"myr\":0.35587,\"ngn\":0.29199,\"nok\":0.11232,\"nzd\":0.4612,\"php\":0.32359,\"pkr\":0.31792,\"pln\":0.17399,\"rub\":0.16098,\"sar\":0.35691,\"sek\":0.28704,\"sgd\":0.31911,\"thb\":0.30733,\"try\":0.34366,\"twd\":0.32309,\"uah\":0.2849,\"usd\":0.35587,\"vef\":0.35587,\"vnd\":0.35587,\"xag\":0.34889,\"xau\":0.36983,\"xdr\":0.11462,\"xlm\":0.54625,\"xrp\":0.61768,\"yfi\":0.68818,\"zar\":0.29749,\"bits\":0.14913,\"link\":0.55247,\"sats\":0.14913},\"price_change_percentage_24h_in_currency\":{\"aed\":-1.88738,\"ars\":-1.69113,\"aud\":-1.51766,\"bch\":0.02692,\"bdt\":-1.93029,\"bhd\":-1.89583,\"bmd\":-1.88204,\"bnb\":-2.89906,\"brl\":-2.43853,\"btc\":0.0,\"cad\":-1.5127,\"chf\":-2.243,\"clp\":-2.07468,\"cny\":-1.77081,\"czk\":-1.81551,\"dkk\":-2.08572,\"dot\":2.82345,\"eos\":-0.02374,\"eth\":-0.32482,\"eur\":-2.09099,\"gbp\":-1.59265,\"hkd\":-1.89848,\"huf\":-2.02466,\"idr\":-1.80123,\"ils\":-1.76539,\"inr\":-1.98987,\"jpy\":-2.51811,\"krw\":-1.63766,\"kwd\":-1.9291,\"lkr\":-1.61455,\"ltc\":-1.17208,\"mmk\":-1.92734,\"mxn\":-2.40356,\"myr\":-1.87098,\"ngn\":-1.92604,\"nok\":-1.61982,\"nzd\":-1.26228,\"php\":-2.21412,\"pkr\":-1.89764,\"pln\":-2.09107,\"rub\":-2.20112,\"sar\":-1.87979,\"sek\":-2.03077,\"sgd\":-1.82039,\"thb\":-1.8554,\"try\":-1.89518,\"twd\":-1.82044,\"uah\":-1.92997,\"usd\":-1.88204,\"vef\":-1.88204,\"vnd\":-1.85896,\"xag\":-1.45438,\"xau\":-1.2144,\"xdr\":-2.09656,\"xlm\":-0.41625,\"xrp\":2.43651,\"yfi\":0.97264,\"zar\":-2.02624,\"bits\":0.09222,\"link\":2.3236,\"sats\":0.09222},\"price_change_percentage_7d_in_currency\":{\"aed\":-7.59394,\"ars\":-6.19855,\"aud\":-6.38868,\"bch\":-1.24679,\"bdt\":-7.84987,\"bhd\":-7.61881,\"bmd\":-7.58891,\"bnb\":-8.18444,\"brl\":-5.31993,\"btc\":0.0,\"cad\":-6.27088,\"chf\":-7.28544,\"clp\":-7.66702,\"cny\":-7.32925,\"czk\":-5.78697,\"dkk\":-6.94901,\"dot\":1.47699,\"eos\":7.79819,\"eth\":-2.69814,\"eur\":-6.97896,\"gbp\":-6.60641,\"hkd\":-7.60657,\"huf\":-5.99195,\"idr\":-6.12146,\"ils\":-7.56926,\"inr\":-7.13343,\"jpy\":-6.69507,\"krw\":-5.71095,\"kwd\":-7.46839,\"lkr\":-7.62585,\"ltc\":-2.60415,\"mmk\":-7.62643,\"mxn\":-7.6518,\"myr\":-6.8014,\"ngn\":-7.92975,\"nok\":-4.80899,\"nzd\":-5.00935,\"php\":-6.38582,\"pkr\":-7.38248,\"pln\":-7.49129,\"rub\":-8.03969,\"sar\":-7.59534,\"sek\":-7.09163,\"sgd\":-6.69405,\"thb\":-6.63974,\"try\":-7.30174,\"twd\":-7.16238,\"uah\":-7.16017,\"usd\":-7.58891,\"vef\":-7.58891,\"vnd\":-7.39348,\"xag\":-5.3098,\"xau\":-5.18784,\"xdr\":-7.6725,\"xlm\":1.99025,\"xrp\":1.52802,\"yfi\":2.82674,\"zar\":-7.19344,\"bits\":0.05326,\"link\":-7.19533,\"sats\":0.05326},\"price_change_percentage_14d_in_currency\":{\"aed\":0.18664,\"ars\":3.32305,\"aud\":-0.14189,\"bch\":2.8628,\"bdt\":0.5167,\"bhd\":0.1903,\"bmd\":0.19482,\"bnb\":-4.33781,\"brl\":-0.01706,\"btc\":0.0,\"cad\":0.46416,\"chf\":-1.19132,\"clp\":-3.06796,\"cny\":0.3726,\"czk\":0.34886,\"dkk\":-0.33625,\"dot\":3.92641,\"eos\":13.07062,\"eth\":-1.88575,\"eur\":-0.34539,\"gbp\":0.56158,\"hkd\":0.17522,\"huf\":0.20268,\"idr\":0.37773,\"ils\":1.42253,\"inr\":0.50336,\"jpy\":1.81419,\"krw\":0.89136,\"kwd\":0.06644,\"lkr\":1.05589,\"ltc\":1.87939,\"mmk\":0.2324,\"mxn\":-1.11618,\"myr\":1.08311,\"ngn\":0.23798,\"nok\":1.80593,\"nzd\":2.12509,\"php\":2.83146,\"pkr\":-1.37707,\"pln\":-1.94614,\"rub\":0.3192,\"sar\":0.17492,\"sek\":-0.82412,\"sgd\":0.49931,\"thb\":0.9272,\"try\":0.94828,\"twd\":0.43242,\"uah\":0.4891,\"usd\":0.19482,\"vef\":0.19482,\"vnd\":0.38318,\"xag\":-0.64781,\"xau\":1.20268,\"xdr\":-0.01939,\"xlm\":10.59994,\"xrp\":7.80641,\"yfi\":4.28948,\"zar\":-0.49741,\"bits\":0.09351,\"link\":-2.98713,\"sats\":0.09351},\"price_change_percentage_30d_in_currency\":{\"aed\":-0.60015,\"ars\":5.65742,\"aud\":-0.91235,\"bch\":8.87012,\"bdt\":-1.42807,\"bhd\":-0.61588,\"bmd\":-0.58952,\"bnb\":2.35712,\"brl\":-4.27146,\"btc\":0.0,\"cad\":-1.89657,\"chf\":-3.78775,\"clp\":-4.22253,\"cny\":-0.53753,\"czk\":-3.48748,\"dkk\":-2.37257,\"dot\":5.2181,\"eos\":10.06396,\"eth\":-5.8299,\"eur\":-2.45229,\"gbp\":-1.94118,\"hkd\":-0.55826,\"huf\":-5.25535,\"idr\":-2.80272,\"ils\":-0.56837,\"inr\":-1.30351,\"jpy\":0.4784,\"krw\":1.26912,\"kwd\":-0.68678,\"lkr\":-0.63975,\"ltc\":-7.75031,\"mmk\":-0.63647,\"mxn\":-3.93567,\"myr\":-1.35635,\"ngn\":-0.60011,\"nok\":0.09999,\"nzd\":0.45575,\"php\":2.55797,\"pkr\":0.02497,\"pln\":-4.06983,\"rub\":4.96439,\"sar\":-0.75814,\"sek\":-0.75474,\"sgd\":-0.8042,\"thb\":-0.82888,\"try\":1.29327,\"twd\":-0.23648,\"uah\":-0.63645,\"usd\":-0.58952,\"vef\":-0.58952,\"vnd\":-0.96327,\"xag\":-11.36173,\"xau\":-2.79044,\"xdr\":-1.65921,\"xlm\":0.42625,\"xrp\":0.0025,\"yfi\":8.20832,\"zar\":-2.92542,\"bits\":0.1017,\"link\":-0.67353,\"sats\":0.1017},\"price_change_percentage_60d_in_currency\":{\"aed\":15.67991,\"ars\":31.48846,\"aud\":18.71808,\"bch\":27.65627,\"bdt\":16.68987,\"bhd\":16.36415,\"bmd\":15.70196,\"bnb\":10.10262,\"brl\":13.0123,\"btc\":0.0,\"cad\":16.12716,\"chf\":11.59654,\"clp\":15.29481,\"cny\":16.04734,\"czk\":11.88634,\"dkk\":12.71254,\"dot\":42.14645,\"eos\":31.3743,\"eth\":1.58865,\"eur\":12.62906,\"gbp\":12.32919,\"hkd\":15.75336,\"huf\":10.86479,\"idr\":14.09317,\"ils\":19.26807,\"inr\":14.7486,\"jpy\":15.27247,\"krw\":18.58938,\"kwd\":15.64081,\"lkr\":2.78146,\"ltc\":25.81103,\"mmk\":16.29702,\"mxn\":13.40648,\"myr\":15.8586,\"ngn\":16.1888,\"nok\":19.25521,\"nzd\":17.73368,\"php\":16.58133,\"pkr\":25.48359,\"pln\":8.93111,\"rub\":27.08402,\"sar\":15.64754,\"sek\":14.17595,\"sgd\":15.47628,\"thb\":15.29967,\"try\":18.98651,\"twd\":16.51609,\"uah\":16.88956,\"usd\":15.70196,\"vef\":15.70196,\"vnd\":14.12291,\"xag\":-0.77081,\"xau\":7.02421,\"xdr\":15.28076,\"xlm\":9.62474,\"xrp\":-4.72104,\"yfi\":4.61788,\"zar\":15.94848,\"bits\":0.07968,\"link\":24.2695,\"sats\":0.07968},\"price_change_percentage_200d_in_currency\":{\"aed\":47.34873,\"ars\":117.91542,\"aud\":41.31081,\"bch\":37.4315,\"bdt\":54.28684,\"bhd\":47.23815,\"bmd\":47.37682,\"bnb\":28.32429,\"brl\":37.41542,\"btc\":0.0,\"cad\":44.66184,\"chf\":33.35031,\"clp\":20.83179,\"cny\":42.65687,\"czk\":26.02615,\"dkk\":31.72874,\"dot\":50.37682,\"eos\":58.44716,\"eth\":-1.41528,\"eur\":31.4515,\"gbp\":32.39181,\"hkd\":47.34809,\"huf\":17.69513,\"idr\":44.78868,\"ils\":51.60084,\"inr\":48.39281,\"jpy\":36.12891,\"krw\":35.91923,\"kwd\":45.65274,\"lkr\":30.12364,\"ltc\":-14.31982,\"mmk\":47.23416,\"mxn\":31.88306,\"myr\":41.05204,\"ngn\":56.8287,\"nok\":43.86237,\"nzd\":35.13198,\"php\":40.3215,\"pkr\":82.89776,\"pln\":25.07065,\"rub\":98.86896,\"sar\":47.14003,\"sek\":36.93475,\"sgd\":36.97884,\"thb\":33.82052,\"try\":54.32037,\"twd\":41.75141,\"uah\":47.22739,\"usd\":47.37682,\"vef\":47.37682,\"vnd\":45.10973,\"xag\":12.02595,\"xau\":23.38918,\"xdr\":46.03677,\"xlm\":83.06243,\"xrp\":41.77316,\"yfi\":42.27546,\"zar\":47.77147,\"bits\":0.10889,\"link\":41.61527,\"sats\":0.10889},\"price_change_percentage_1y_in_currency\":{\"aed\":-32.13974,\"ars\":29.77099,\"aud\":-24.52211,\"bch\":82.62847,\"bdt\":-16.51327,\"bhd\":-32.1322,\"bmd\":-32.1268,\"bnb\":-13.23188,\"brl\":-25.86134,\"btc\":0.0,\"cad\":-26.45913,\"chf\":-36.22055,\"clp\":-33.99545,\"cny\":-27.1689,\"czk\":-35.19833,\"dkk\":-32.8173,\"dot\":114.04442,\"eos\":73.55006,\"eth\":9.31462,\"eur\":-32.9314,\"gbp\":-28.44261,\"hkd\":-32.09133,\"huf\":-31.67124,\"idr\":-29.24981,\"ils\":-22.8722,\"inr\":-26.95947,\"jpy\":-29.10361,\"krw\":-26.95754,\"kwd\":-31.90272,\"lkr\":-33.61987,\"ltc\":-15.05376,\"mmk\":-23.0865,\"mxn\":-38.85277,\"myr\":-29.65407,\"ngn\":-24.87547,\"nok\":-18.27742,\"nzd\":-24.87903,\"php\":-27.47705,\"pkr\":3.55587,\"pln\":-33.35999,\"rub\":-33.32552,\"sar\":-32.1475,\"sek\":-25.84697,\"sgd\":-33.58969,\"thb\":-30.99474,\"try\":-10.26377,\"twd\":-28.81352,\"uah\":-14.81588,\"usd\":-32.1268,\"vef\":-32.1268,\"vnd\":-30.55594,\"xag\":-32.1787,\"xau\":-33.20493,\"xdr\":-30.3157,\"xlm\":42.33136,\"xrp\":9.10152,\"yfi\":59.12522,\"zar\":-18.26914,\"bits\":0.05628,\"link\":28.10518,\"sats\":0.05628},\"market_cap_change_24h_in_currency\":{\"aed\":-50550439550.5459,\"ars\":-2747166373624.1094,\"aud\":-16278963630.7417,\"bch\":-683109.3621377945,\"bdt\":-1486329823227.6406,\"bhd\":-5188686153.559906,\"bmd\":-13735066650.221252,\"bnb\":-49365710.56470394,\"brl\":-83769413690.0791,\"btc\":781.0,\"cad\":-15494348669.292847,\"chf\":-13217540183.484192,\"clp\":-12040495543281.312,\"cny\":-90272121740.5625,\"czk\":-275898975838.05273,\"dkk\":-97046396173.94775,\"dot\":2474377794,\"eos\":-819743400.8817749,\"eth\":-1294445.8132476807,\"eur\":-13019643643.180542,\"gbp\":-9405712058.476135,\"hkd\":-108547688415.74316,\"huf\":-4855371079070.781,\"idr\":-195527530810938.0,\"ils\":-47652772801.90332,\"inr\":-1167724878198.8594,\"jpy\":-2290801798796.3438,\"krw\":-15675031438507.5,\"kwd\":-4229166521.3157654,\"lkr\":-3946582649648.75,\"ltc\":-69463576.10519409,\"mmk\":-29367204573822.5,\"mxn\":-291812230298.52734,\"myr\":-60678340066.37402,\"ngn\":-6437356245485.656,\"nok\":-118834422051.10352,\"nzd\":-16026375007.76941,\"php\":-863471594815.1523,\"pkr\":-3916970235051.0938,\"pln\":-59930401146.05713,\"rub\":-1181722002854.5781,\"sar\":-51299526059.630615,\"sek\":-140620015713.47852,\"sgd\":-17551366408.22754,\"thb\":-463397419649.83203,\"try\":-268005465033.9707,\"twd\":-400500085034.5449,\"uah\":-517025700363.2422,\"usd\":-13735066650.221252,\"vef\":-1375292223.6866302,\"vnd\":-319652073083040.0,\"xag\":-399319921.8617363,\"xau\":-4976714.1229172945,\"xdr\":-11082537251.209229,\"xlm\":-25164657909.68164,\"xrp\":30178421725,\"yfi\":728792,\"zar\":-248441292442.06055,\"bits\":9156570310,\"link\":1636034374,\"sats\":915657031001},\"market_cap_change_percentage_24h_in_currency\":{\"aed\":-2.47666,\"ars\":-2.2732,\"aud\":-1.971,\"bch\":-0.01545,\"bdt\":-2.51932,\"bhd\":-2.47653,\"bmd\":-2.47135,\"bnb\":-2.88502,\"brl\":-2.96967,\"btc\":0.00404,\"cad\":-2.06893,\"chf\":-2.65972,\"clp\":-2.72667,\"cny\":-2.36078,\"czk\":-2.31709,\"dkk\":-2.57002,\"dot\":2.83544,\"eos\":-0.16257,\"eth\":-0.45385,\"eur\":-2.56956,\"gbp\":-2.10542,\"hkd\":-2.48814,\"huf\":-2.5391,\"idr\":-2.35434,\"ils\":-2.34695,\"inr\":-2.55742,\"jpy\":-3.06179,\"krw\":-2.13082,\"kwd\":-2.48313,\"lkr\":-2.20547,\"ltc\":-1.13006,\"mmk\":-2.51638,\"mxn\":-2.90333,\"myr\":-2.46036,\"ngn\":-2.51509,\"nok\":-2.02215,\"nzd\":-1.78025,\"php\":-2.76853,\"pkr\":-2.48686,\"pln\":-2.56597,\"rub\":-2.60742,\"sar\":-2.46098,\"sek\":-2.45032,\"sgd\":-2.36893,\"thb\":-2.42734,\"try\":-2.48543,\"twd\":-2.35745,\"uah\":-2.51899,\"usd\":-2.47135,\"vef\":-2.47135,\"vnd\":-2.44841,\"xag\":-1.81999,\"xau\":-1.7921,\"xdr\":-2.68458,\"xlm\":-0.44104,\"xrp\":2.66562,\"yfi\":1.1083,\"zar\":-2.46684,\"bits\":0.04734,\"link\":2.29294,\"sats\":0.04734},\"total_supply\":21000000.0,\"max_supply\":21000000.0,\"circulating_supply\":19352337.0,\"last_updated\":\"2023-04-21T11:59:25.259Z\"},\"public_interest_stats\":{\"alexa_rank\":9440,\"bing_matches\":null},\"status_updates\":[],\"last_updated\":\"2023-04-21T11:59:25.259Z\"}"
    private val coinChartDetailExpectedResponse = "{\"prices\":[[1681603200000,30312.16187965924],[1681689600000,30304.807514785844],[1681776000000,29467.459829260344],[1681862400000,30365.904167541412],[1681948800000,28833.217501221105],[1682035200000,28255.57824866478],[1682121600000,27300.157128515566],[1682139164000,27397.098835586115]],\"market_caps\":[[1681603200000,586969785778.0928],[1681689600000,586268225587.8677],[1681776000000,569958059900.5195],[1681862400000,587730212230.9563],[1681948800000,558796473890.5958],[1682035200000,546141074655.3764],[1682121600000,528357279464.0127],[1682139164000,529735931631.89453]],\"total_volumes\":[[1681603200000,11028264273.146706],[1681689600000,11188035367.077023],[1681776000000,16771824487.579357],[1681862400000,18078539248.52104],[1681948800000,24136616861.636356],[1682035200000,21695904964.799046],[1682121600000,20420333533.71415],[1682139164000,19998688082.4424]]}"
    private val mockRemoteRepository = Mockito.mock<RemoteRepository>()

    private lateinit var viewModel: CoinDetailViewModel


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CoinDetailViewModel(mockRemoteRepository)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `test getCoinDetail() success`() = runTest {
        // Given
        val coinId = "bitcoin"

        val expectedData =
            Gson().fromJson(coinDetailExpectedResponse, CoinDetailResponse::class.java)
        whenever(
            mockRemoteRepository.get(
                CoinDetailResponse::class.java,
                coinId + AppConstants.COIN_DETAIL_URL
            )
        ).thenReturn(
            Response.success(expectedData, 200)
        )

        // When
        val actualData = viewModel.getCoinDetail(coinId).value?.peekContent()

        // Then
        assertEquals(Resource.Status.SUCCESS, actualData?.status)
        assertEquals("Cryptocurrency", actualData?.data?.categories?.get(0))
    }

    @Test
    fun `test getCoinDetail() error`() = runTest {
        // Given
        val coinId = "bitcoin"
        val errorMessage = "Error message"
        whenever(
            mockRemoteRepository.get(
                CoinDetailResponse::class.java,
                coinId + AppConstants.COIN_DETAIL_URL
            )
        ).thenReturn(Response.error(errorMessage, 404))

        // When
        viewModel.getCoinDetail(coinId)

        // Then
        val actualData = viewModel.getCoinDetail(coinId).value?.peekContent()
        // Then
        assertEquals(Resource.Status.ERROR, actualData?.status)
        assertEquals(errorMessage, actualData?.message)
    }

    @Test
    fun `test getCoinDetail() empty`() = runTest {
        // Given
        val coinId = "bitcoin"
        whenever(
            mockRemoteRepository.get(
                CoinDetailResponse::class.java,
                coinId + AppConstants.COIN_DETAIL_URL
            )
        ).thenReturn(Response.success(null, 200))

        // When
        viewModel.getCoinDetail(coinId)

        // Then
        val actualData = viewModel.getCoinDetail(coinId).value?.peekContent()
        // Then
        assertEquals(Resource.Status.EMPTY, actualData?.status)
        assertEquals("No Data Found.", actualData?.message)
    }

    @Test
    fun `test getCoinChartDetail() success`() = runTest {
        // Given
        val coinId = "bitcoin"

        val expectedData =
            Gson().fromJson(coinChartDetailExpectedResponse, CoinChartDataResponse::class.java)
        whenever(
            mockRemoteRepository.get(
                CoinChartDataResponse::class.java,
                coinId + AppConstants.COIN_CHART_DATA
            )
        ).thenReturn(
            Response.success(expectedData, 200)
        )

        // When
        val actualData = viewModel.getCoinChartData(coinId).value?.peekContent()

        // Then
        assertEquals(Resource.Status.SUCCESS, actualData?.status)
    }

    @Test
    fun `test getCoinChartDetail() error`() = runTest {
        // Given
        val coinId = "bitcoin"
        val errorMessage = "Error message"

        val expectedData =
            Gson().fromJson(coinChartDetailExpectedResponse, CoinChartDataResponse::class.java)
        whenever(
            mockRemoteRepository.get(
                CoinChartDataResponse::class.java,
                coinId + AppConstants.COIN_CHART_DATA
            )
        ).thenReturn(
            Response.error(errorMessage, 404)
        )

        // When
        val actualData = viewModel.getCoinChartData(coinId).value?.peekContent()

        // Then
        assertEquals(Resource.Status.ERROR, actualData?.status)
        assertEquals(null, actualData?.data)
        assertEquals(errorMessage, actualData?.message)
    }

    @Test
    fun `test getCoinChartDetail() empty`() = runTest {
        // Given
        val coinId = "bitcoin"
        whenever(
            mockRemoteRepository.get(
                CoinChartDataResponse::class.java,
                coinId + AppConstants.COIN_CHART_DATA
            )
        ).thenReturn(
            Response.success(null, 200)
        )

        // When
        val actualData = viewModel.getCoinChartData(coinId).value?.peekContent()

        // Then
        assertEquals(Resource.Status.EMPTY, actualData?.status)
        assertEquals(null, actualData?.data)
    }


}