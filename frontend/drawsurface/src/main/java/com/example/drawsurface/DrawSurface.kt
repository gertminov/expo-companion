package com.example.drawsurface

import android.graphics.Bitmap
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.MutableLiveData


    @Composable
    fun DrawSurface(
        initCount: MutableLiveData<Int>,
        restartCount: MutableLiveData<Int>,
        redoCount: MutableLiveData<Int>,
        undoCount: MutableLiveData<Int>,
        refreshCount: MutableLiveData<Int>,
        isEraser: Boolean,
        initBmp: Bitmap?,
        bgBmp: Bitmap?,
        initialPageIdx: Int,
        notifyBitmapUpdate: (Bitmap)-> Unit,
        notifyUndoStateChanged: (Boolean, Boolean)-> Unit,
    ) {

        BoxWithConstraints {
            val initState = initCount.observeAsState(0)
            val restartCountState = restartCount.observeAsState(0)
            val undoCountState = undoCount.observeAsState(0)
            val redoCountState = redoCount.observeAsState(0)
            val refreshCountState = refreshCount.observeAsState(0)
            AndroidView(modifier = Modifier.size(maxWidth, maxHeight),
                factory = {context->
                    CanvasBoox(context, initBmp, bgBmp, initialPageIdx, BitmapUtil.bitmapLock).apply {
                        firstInit()
                        setOnUpdateListener { notifyBitmapUpdate(it) }
                        setOnUndoStateListener { undo, redo-> notifyUndoStateChanged(undo, redo) }
                    }
                },
                update = {
                    it.ensureInit(initState.value)
                    it.penOrEraser(!isEraser)
//                it.onPageIdx(idxState.value, bitmapLoader= {idx->
//                    bookIO.loadBitmapOrNull(book.getPage(idx)).also {
//                        isDirty = false
//                        pageBmp = it
//                    }
//                })
                    it.onRestart(restartCountState.value!!)
                    it.undo(undoCountState.value)
                    it.redo(redoCountState.value)
                    it.refreshUI(refreshCountState.value)
                }
            )
        }

    }
