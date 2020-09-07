package com.photoroomtest.gallery.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.photoroomtest.R
import com.photoroomtest.interface_adapter.weather.model.GalleryImageUiModel
import com.photoroomtest.util.inflateFromParent
import com.photoroomtest.util.loadImageB64
import kotlinx.android.synthetic.main.gallery_image_item.view.*

class GalleryListAdapter : RecyclerView.Adapter<GalleryListAdapter.DailyWeatherListViewHolder>() {

    var imageList: MutableList<GalleryImageUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherListViewHolder {
        return DailyWeatherListViewHolder(
            parent
        )
    }

    override fun onBindViewHolder(holder: DailyWeatherListViewHolder, position: Int) {
        val dailyWeatherView = imageList[position]
        holder.bind(dailyWeatherView)
    }

    override fun getItemCount(): Int = imageList.size

    fun addGalleryImage(galleryImageUiModel: GalleryImageUiModel) {
        val insertionIndex = imageList.size
        imageList.add(galleryImageUiModel)
        if (insertionIndex == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemInserted(insertionIndex)
        }

    }

    class DailyWeatherListViewHolder(
        itemView: ViewGroup
    ) : RecyclerView.ViewHolder(itemView.inflateFromParent(R.layout.gallery_image_item)) {
        fun bind(galleryImageUiModel: GalleryImageUiModel) = with(itemView) {
            original_image.loadImageB64(galleryImageUiModel.originalImage)
            transformed_image.loadImageB64(galleryImageUiModel.transformedImage)
        }
    }
}
