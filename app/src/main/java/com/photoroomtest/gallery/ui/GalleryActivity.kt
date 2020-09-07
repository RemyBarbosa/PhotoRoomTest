package com.photoroomtest.gallery.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.photoroomtest.R
import com.photoroomtest.gallery.ui.adapter.GalleryListAdapter
import com.photoroomtest.interface_adapter.base.ErrorState
import com.photoroomtest.interface_adapter.base.LoadingState
import com.photoroomtest.interface_adapter.weather.GalleryViewModel
import com.photoroomtest.interface_adapter.weather.model.GalleryImageUiModel
import com.photoroomtest.util.hide
import com.photoroomtest.util.show
import kotlinx.android.synthetic.main.content_gallery.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.IOException


class GalleryActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    private val viewModel by viewModel<GalleryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            gallery_error.hide()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }

        observeViewModel()
    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun observeViewModel() {
        viewModel.states.observe(this, Observer { state ->
            state?.let {
                when (state) {
                    is LoadingState -> showLoader()
                    is ErrorState -> showError(state.error)
                    is GalleryViewModel.GalleryImageState -> showGalleryImage(state.galleryImageUiModel)
                }
            }
        })
    }

    private fun showGalleryImage(galleryImageUiModel: GalleryImageUiModel) {
        if (gallery_image_list.adapter == null) {
            gallery_image_list.adapter = GalleryListAdapter()
        }
        (gallery_image_list.adapter as GalleryListAdapter).addGalleryImage(galleryImageUiModel)
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            data?.data?.let { fileUri ->
                val inputStream = FileInputStream(
                    contentResolver.openFileDescriptor(fileUri, "r")?.fileDescriptor
                )

                val bytes: ByteArray
                val buffer = ByteArray(8192)
                var bytesRead: Int
                val output = ByteArrayOutputStream()

                try {
                    while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                        output.write(buffer, 0, bytesRead)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                bytes = output.toByteArray()
                val encodedString: String = Base64.encodeToString(bytes, Base64.DEFAULT)
                viewModel.upload(encodedString)
            }
        }
    }
}
