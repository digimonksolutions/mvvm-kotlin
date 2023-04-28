package com.cryptocapital.ui.fragment.coinDetail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.navigation.fragment.navArgs
import com.cryptocapital.R
import com.cryptocapital.base.BaseFragment
import com.cryptocapital.data.Resource
import com.cryptocapital.databinding.FragmentCoinDetailBinding
import com.cryptocapital.extensions.toastMessage
import com.cryptocapital.model.CoinChartDataResponse
import com.cryptocapital.model.CoinDetailResponse
import com.cryptocapital.ui.fragment.coinDetail.viewmodel.CoinDetailViewModel
import com.cryptocapital.utils.CustomMarkerView
import com.cryptocapital.utils.Utils.Companion.monetaryDescription
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding, CoinDetailViewModel>() {
    override val layoutId: Int
        get() = R.layout.fragment_coin_detail
    override val viewModel: CoinDetailViewModel by viewModel()
    private val navArgs by navArgs<CoinDetailFragmentArgs>()

    override fun onReady(savedInstanceState: Bundle?) {
        binding.mainLayout.setBackgroundColor(ColorUtils.setAlphaComponent(navArgs.color,70))
        getCoinDetailData()
        getCoinChartData()
        binding.btnBack.setOnClickListener { viewModel.navigateBack() }
    }

    private fun getCoinDetailData() {
        viewModel.getCoinDetail(navArgs.coinID).observe(viewLifecycleOwner) { data ->
            when (data.getContentIfNotHandled()?.status) {
                Resource.Status.SUCCESS -> {
                    setupUi(data.peekContent().data!!)
                }

                Resource.Status.ERROR -> {
                    requireContext().toastMessage(data.peekContent().message.toString())
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.EMPTY -> {
                    requireContext().toastMessage(data.peekContent().message.toString())
                }

                else -> {}
            }
        }

    }

    private fun getCoinChartData() {
        viewModel.getCoinChartData(navArgs.coinID).observe(viewLifecycleOwner) { data ->
            when (data.getContentIfNotHandled()?.status) {
                Resource.Status.SUCCESS -> {
                    setupChart(data.peekContent().data!!)
                }

                Resource.Status.ERROR -> {
                    requireContext().toastMessage(data.peekContent().message.toString())
                }

                Resource.Status.LOADING -> {
                }

                Resource.Status.EMPTY -> {
                }

                else -> {}
            }
        }

    }

    private fun setupChart(data: CoinChartDataResponse) {

        val entries: MutableList<Entry> = ArrayList()
        for (i in 0 until data.prices.size) {
            entries.add(
                Entry(
                    data.prices.get(i).get(0).toFloat(),
                    data.prices.get(i).get(1).toFloat()
                )
            )
        }
        val dataSet = LineDataSet(entries, "")

        // 4. Customize the chart:
        dataSet.color = navArgs.color
        dataSet.valueTextColor = Color.BLACK
        dataSet.fillColor = Color.parseColor("#FEE8E6")
        dataSet.setDrawFilled(true)
        dataSet.lineWidth = 1.5f
        dataSet.setDrawValues(false)
        dataSet.setDrawCircles(false)


        // 5. Create a LineData object with the data set:
        val lineData = LineData(dataSet)


        // 6. Set the data to the chart:
        binding.coinChart.setData(lineData)

        // 7. Customize the chart further, if needed:

        binding.coinChart.getDescription().setEnabled(false)
        binding.coinChart.legend.isEnabled = false
        binding.coinChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.coinChart.xAxis.setDrawGridLines(false)
        binding.coinChart.xAxis.setDrawAxisLine(false)
        binding.coinChart.getXAxis().setValueFormatter(object : IndexAxisValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return SimpleDateFormat("MMM dd", Locale.US).format(Date(value.toLong()))
            }
        })
        val rightAxis: YAxis = binding.coinChart.getAxisRight()
        rightAxis.isEnabled = true
        rightAxis.valueFormatter = object : IndexAxisValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toDouble().monetaryDescription
            }
        }
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        rightAxis.setDrawGridLines(true)
        rightAxis.setDrawAxisLine(false)

        val leftAxis: YAxis = binding.coinChart.getAxisLeft()
        leftAxis.isEnabled = false
        binding.coinChart.animateXY(1500, 1500, Easing.EaseInOutSine)
        binding.coinChart.setDrawMarkers(true)
        binding.coinChart.marker = CustomMarkerView(requireContext(),R.layout.chart_marker_layout)
        binding.coinChart.invalidate()

    }

    private fun setupUi(data: CoinDetailResponse) {
        val bottomSheetBehaviour = BottomSheetBehavior.from(binding.bottomSheet.standardBottomSheet)
        binding.coinName = data.name
        binding.coinSymbol = data.symbol
        binding.coinPrice = "$${String.format("%.2f", data.market_data.current_price.usd)}"
        binding.bottomSheet.tvOverViewBody.text = Html.fromHtml(data.description.en, Html.FROM_HTML_MODE_LEGACY)
        binding.bottomSheet.coinCurrentPrice = "$${String.format("%.2f", data.market_data.current_price.usd)}"
        binding.bottomSheet.coinMarketCap = data.market_data.market_cap.usd.toDouble().monetaryDescription
        binding.bottomSheet.coinChangePer = String.format("%.2f", data.market_data.price_change_percentage_24h) + "%"
        binding.coinPricePer =  String.format("%.2f", data.market_data.price_change_percentage_24h) + "%"
        binding.bottomSheet.coinChangeMarketCapPer =
            String.format("%.2f", data.market_data.market_cap_change_percentage_24h) + "%"
        if (data.market_data.price_change_percentage_24h > 0) {
            binding.bottomSheet.tvPriceChangePer.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.tvCoinPricePer.setBackgroundResource(R.drawable.bg_green_box)

            binding.bottomSheet.tvPriceChangePer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_arrow_up
                ), null, null, null
            )
        } else {
            binding.bottomSheet.tvPriceChangePer.setTextColor(Color.RED)
            binding.bottomSheet.tvPriceChangePer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_arrow_down
                ), null, null, null
            )
            binding.tvCoinPricePer.setBackgroundResource(R.drawable.bg_red_box)
        }

        if (data.market_data.market_cap_change_percentage_24h > 0) {
            binding.bottomSheet.tvMarketCapChangePer.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.bottomSheet.tvMarketCapChangePer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_up),
                null,
                null,
                null
            )
        } else {
            binding.bottomSheet.tvMarketCapChangePer.setTextColor(Color.RED)
            binding.bottomSheet.tvMarketCapChangePer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_down),
                null,
                null,
                null
            )
        }
    }


    override fun onNetworkAvailable() {
    }

    override fun onNetworkLost() {
    }
}