package com.example.expo_companion.ui.viewModels

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expo_companion.data.DrawSurfaceFree
import com.example.expo_companion.data.NoteSurface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DrawingViewModel @Inject constructor() : ViewModel() {
    var isDirty = false
    var lastWritten = -1L
    var pageBmp: Bitmap? = null
    lateinit var question: NoteSurface
    val bitmapLock = java.util.concurrent.locks.ReentrantLock()

    /**
     * This flow indicates wether another canvas is currently using the RawDrawingApi
     */
    var isFree = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            //if isFree is true, the flow gets propagated to the local "isFree", if the global Flow changes to false
            // it does not update, to avoid a feedback loop
            DrawSurfaceFree.isFree.collectLatest { free ->
                if (free) {
                    isFree.update { true }
                } }
        }
    }
    fun init(question: NoteSurface) {
        this.question = question
        pageBmp = question.getBmp()
    }

    fun notifyBitmapUpdate(newBmp: Bitmap) {
        isDirty = true
        lastWritten = (Date()).time
        pageBmp = newBmp
    }

    val initCount = MutableLiveData(0)
    fun updateInitCount(num: Int) {
        if (num != initCount.value) {
            initCount.value = num
        }
    }
    var restartCount = MutableLiveData(0)
    val refreshCount = MutableLiveData(0)

    var refreshUI: ()-> Unit = {}

    fun safeBitmap() {
        pageBmp?.let {
            question.saveBmpAndUpdate(it)
        }
    }

}