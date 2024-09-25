package com.mvvm.cryptocapital.ui.fragment.coinList.adapter

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mvvm.cryptocapital.model.CoinListResponse
import com.mvvm.cryptocapital.R
import com.mvvm.cryptocapital.databinding.CoinListItemLayoutBinding
import com.mvvm.cryptocapital.ui.fragment.coinList.viewmodel.CoinListViewModel
import com.mvvm.cryptocapital.base.BaseAdapters
import com.mvvm.cryptocapital.base.BaseViewHolder

class CoinListAdapter(
    data:ArrayList<CoinListResponse.CoinListResponseItem>,
    viewModel:CoinListViewModel
): BaseAdapters<CoinListItemLayoutBinding, CoinListViewModel, CoinListResponse.CoinListResponseItem>(data, viewModel) {
    override val layoutId: Int
        get() = R.layout.coin_list_item_layout

    override fun bind(
        binding: CoinListItemLayoutBinding,
        item: CoinListResponse.CoinListResponseItem,
        position: Int,
        holder: BaseViewHolder<CoinListItemLayoutBinding>
    ) {
        var color = Color.WHITE
        binding.tvCoinName.text = item.name
        binding.tvCoinSymbol.text = item.symbol
        Glide.with(binding.root.context).asBitmap().load(item.image).diskCacheStrategy(DiskCacheStrategy.ALL).skipMemoryCache(false).addListener(
            object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Palette.from(resource!!).generate {
                        color = it!!.getVibrantColor(ContextCompat.getColor(binding.root.context,R.color.white))
                    }
                    return false
                }

            }).into(binding.ivCoinImage)
        binding.tvCoinPrice.text = "$${String.format("%.2f",item.current_price)}"
        binding.tvCoinPer.text = "${String.format("%.2f",item.price_change_percentage_24h)}%"
        if (item.price_change_percentage_24h > 0){
            binding.tvCoinPer.setTextColor(ContextCompat.getColor(binding.root.context,R.color.green))
        }else{
            binding.tvCoinPer.setTextColor(Color.RED)
        }
        holder.itemView.setOnClickListener {
            viewModel.navigateToCoinDetailPage(item.id,color)
        }

    }
}