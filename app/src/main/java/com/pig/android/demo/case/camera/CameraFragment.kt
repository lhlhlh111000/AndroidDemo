package com.pig.android.demo.case.camera

import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

typealias LumaListener = (luma: Double) -> Unit

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/11/19
 */
class CameraFragment : Fragment() {

    companion object {
        private const val TAG = "CameraSample"
    }

    private lateinit var cameraExecutor: ExecutorService

    private lateinit var outputDir: File

    private var imageCapture: ImageCapture? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_camera, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_take_photo.setOnClickListener {
            takePhoto()
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
        outputDir = getOutputDir()

        startCamera()
    }

    override fun onDestroy() {
        cameraExecutor.shutdown()
        super.onDestroy()
    }

    private fun startCamera() {
        val cameraProviderFuture
                = context?.let { ProcessCameraProvider.getInstance(it) }

        cameraProviderFuture?.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(pre_view.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, LumaAnalyzer{ luma ->
                        val isMainThread = Looper.myLooper() == Looper.getMainLooper();
                        Log.d(TAG, "Average luminosity: $luma, isMainThread: $isMainThread")
                    })
                }

            try {
                cameraProvider.unbindAll()
                val camera = cameraProvider.bindToLifecycle(this,
                    cameraSelector, preview, imageCapture, imageAnalyzer)
            }catch (e: Exception) {
                e.printStackTrace()
            }

        }, ContextCompat.getMainExecutor(context))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val photoFile = File(
            outputDir, SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US)
                .format(System.currentTimeMillis()) + ".jpg"
        )
        val outputOptions= ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "Photo capture succeeded: $savedUri"
                    Log.e(TAG, msg)
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                }
            }
        )
    }

    private fun getOutputDir() : File {
        val mediaDir = activity?.externalMediaDirs?.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if(mediaDir != null && mediaDir.exists()) mediaDir else activity?.filesDir!!
    }
}