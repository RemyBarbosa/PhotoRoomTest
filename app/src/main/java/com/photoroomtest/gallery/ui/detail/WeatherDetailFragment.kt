package com.photoroomtest.gallery.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.photoroomtest.R
import com.photoroomtest.gallery.ui.adapter.WeatherListAdapter
import com.photoroomtest.interface_adapter.base.ErrorState
import com.photoroomtest.interface_adapter.base.LoadingState
import com.photoroomtest.interface_adapter.weather.HourlyWeatherViewModel
import com.photoroomtest.interface_adapter.weather.model.WeatherUIModel
import kotlinx.android.synthetic.main.fragment_weather_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherDetailFragment : Fragment() {

    companion object {
        private const val COUNT = 24
    }

    val args: WeatherDetailFragmentArgs by navArgs()

    private val viewModel by viewModel<HourlyWeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        viewModel.observeHourlyWeatherList(args.latitude, args.longitude,
            COUNT, args.units, args.appId)
    }

    private fun observeViewModel() {
        viewModel.states.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                when (state) {
                    is LoadingState -> showLoader()
                    is ErrorState -> showError(state.error)
                    is HourlyWeatherViewModel.HourlyWeatherListState -> showHourlyWeatherList(state.weatherUIModelList)
                }
            }
        })
    }

    private fun showHourlyWeatherList(weatherUIModelList: List<WeatherUIModel>) {
        if (gallery_image_list.adapter == null) {
            gallery_image_list.adapter =
                WeatherListAdapter()

        }
        (gallery_image_list.adapter as WeatherListAdapter).weatherList = weatherUIModelList.toMutableList()
        progress_bar.hide()

    }

    private fun showError(error: Throwable) {
        progress_bar.hide()
        gallery_image_list.hide()
        daily_weather_error.show()
        daily_weather_error.text = error.toString()
    }

    private fun showLoader() {
        progress_bar.show()
    }
}
