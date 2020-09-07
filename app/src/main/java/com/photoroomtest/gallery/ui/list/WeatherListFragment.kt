package com.photoroomtest.gallery.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.photoroomtest.R
import com.photoroomtest.gallery.ui.adapter.WeatherListAdapter
import com.photoroomtest.interface_adapter.base.ErrorState
import com.photoroomtest.interface_adapter.base.LoadingState
import com.photoroomtest.interface_adapter.weather.GalleryViewModel
import com.photoroomtest.interface_adapter.weather.model.WeatherUIModel
import kotlinx.android.synthetic.main.fragment_weather_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherListFragment : Fragment() {

    companion object {
        private const val LATITUDE = 48.853f
        private const val LONGITUDE = 2.3488f
        private const val COUNT = 10
        private const val UNITS = "metric"
        private const val APP_ID = "e373fbdfb7c805a59762e6388e9ede6b"
    }

    private val viewModel by viewModel<GalleryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hourly_weather_button.setOnClickListener {
            val action =
                WeatherListFragmentDirections.actionWeatherListFragmentToWeatherDetailFragment(
                    LATITUDE,
                    LONGITUDE,
                    UNITS,
                    APP_ID
                )
            findNavController().navigate(action)
        }

        observeViewModel()
        viewModel.observeDailyWeatherList(
            LATITUDE,
            LONGITUDE,
            COUNT,
            UNITS,
            APP_ID
        )
    }


    private fun observeViewModel() {
        viewModel.states.observe(viewLifecycleOwner, Observer { state ->
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
        daily_weather_error.show()
        daily_weather_error.text = error.toString()
    }

    private fun showLoader() {
        progress_bar.show()
    }
}
