package com.photoroomtest.gallery.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.photoroomtest.R
import com.photoroomtest.interface_adapter.weather.model.WeatherUIModel
import com.photoroomtest.util.inflateFromParent

class GalleryListAdapter : RecyclerView.Adapter<GalleryListAdapter.DailyWeatherListViewHolder>() {

    var weatherList: MutableList<WeatherUIModel> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherListViewHolder {
        return DailyWeatherListViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(holder: DailyWeatherListViewHolder, position: Int) {
        val dailyWeatherView = weatherList[position]
        holder.bind(dailyWeatherView)
    }

    override fun getItemCount(): Int = weatherList.size

    class DailyWeatherListViewHolder(
        itemView: ViewGroup
    ) : RecyclerView.ViewHolder(itemView.inflateFromParent(R.layout.gallery_image_item)) {
        fun bind(weatherUIModel: WeatherUIModel) = with(itemView) {
        }
    }
}
