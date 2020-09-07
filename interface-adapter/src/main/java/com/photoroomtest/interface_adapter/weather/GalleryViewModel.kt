package com.photoroomtest.interface_adapter.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.photoroomtest.interface_adapter.base.ErrorState
import com.photoroomtest.interface_adapter.base.LoadingState
import com.photoroomtest.interface_adapter.base.RxViewModel
import com.photoroomtest.interface_adapter.base.State
import com.photoroomtest.interface_adapter.weather.model.GalleryImageUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GalleryViewModel(
    private val galleryManager: GalleryManager
) : RxViewModel() {

    private val mStates = MutableLiveData<State>()
    val states: LiveData<State>
        get() = mStates

    fun upload(
        b64Image: String
    ) {
        mStates.value = LoadingState
        launch {
            galleryManager.upload(b64Image)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(this::onGalleryImageReceive, this::onError)
        }
    }

    private fun onGalleryImageReceive(galleryImageUiModel: GalleryImageUiModel) {
        mStates.value = GalleryImageState(galleryImageUiModel)
    }

    private fun onError(error: Throwable) {
        mStates.value =
            ErrorState(error)
    }

    data class GalleryImageState(val galleryImageUiModel: GalleryImageUiModel) : State()
}