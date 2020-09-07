package com.photoroomtest.gallery.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.photoroomtest.R
import com.photoroomtest.gallery.ui.adapter.WeatherListAdapter
import com.photoroomtest.interface_adapter.base.ErrorState
import com.photoroomtest.interface_adapter.base.LoadingState
import com.photoroomtest.interface_adapter.weather.GalleryViewModel
import com.photoroomtest.interface_adapter.weather.model.WeatherUIModel
import com.photoroomtest.util.hide
import com.photoroomtest.util.show
import kotlinx.android.synthetic.main.content_gallery.*
import kotlinx.android.synthetic.main.fragment_weather_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class GalleryActivity : AppCompatActivity() {

    private val viewModel by viewModel<GalleryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.states.observe(this, Observer { state ->
            state?.let {
                when (state) {
                    is LoadingState -> showLoader()
                    is ErrorState -> showError(state.error)
                    is GalleryViewModel.DailyWeatherListState -> showDailyWeatherList(state.weatherUIModelList)
                }
            }
        })
    }

    private fun showDailyWeatherList(weatherUIModelList: List<WeatherUIModel>) {
        if (gallery_image_list.adapter == null) {
            gallery_image_list.adapter =
                WeatherListAdapter()

        }
        (gallery_image_list.adapter as WeatherListAdapter).weatherList = weatherUIModelList.toMutableList()
        progress_bar.hide()

    }

    private fun showError(error: Throwable) {
        progress_bar.hide()
        gallery_error.show()
        gallery_error.text = error.toString()
    }

    private fun showLoader() {
        progress_bar.show()
    }
}
