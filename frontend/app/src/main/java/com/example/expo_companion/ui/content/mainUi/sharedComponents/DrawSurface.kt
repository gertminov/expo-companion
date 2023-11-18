package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.drawsurface.CanvasBoox
import com.example.expo_companion.data.DrawSurfaceFree
import com.example.expo_companion.data.NoteSurface
import com.example.expo_companion.ui.viewModels.DrawingViewModel
import kotlinx.coroutines.flow.update

@Composable
fun DrawSurface(
    isPenSelected: MutableState<Boolean>,
    noteSurface: NoteSurface,
    viewModel: DrawingViewModel = hiltViewModel(),
) {
    val isFree by viewModel.isFree.collectAsState()
    viewModel.init(noteSurface)

    DisposableEffect(key1 = null) {
        onDispose {
            viewModel.safeBitmap()
            DrawSurfaceFree.isFree.update { true }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize().padding(top = 20.dp)
    ) {
        if (isFree) //ensures, that the canvas is only rendered, if no other canvas is using the RawDrawingApi
            BoxWithConstraints {
                val initState = viewModel.initCount.observeAsState(0)
                val restartCount = viewModel.restartCount.observeAsState(0)
                val refreshState = viewModel.refreshCount.observeAsState(initial = 0)

                AndroidView(
                    modifier = Modifier.size(maxWidth, maxHeight),
                    factory = { context ->
                        CanvasBoox(
                            context,
                            initialBmp = viewModel.pageBmp,
                            background = null,
                            bitmapLock = viewModel.bitmapLock,
                            hasBackground = true
                        ).apply {
                            firstInit()
                            setOnUpdateListener { viewModel.notifyBitmapUpdate(it) }
                            viewModel.refreshUI = { refreshUI() }
                            DrawSurfaceFree.isFree.update { false }
                        }
                    },
                    update = {
                        it.ensureInit(initState.value)
                        it.penOrEraser(isPenSelected.value)
                        it.onRestart(restartCount.value)
                        it.refreshUI(refreshState.value)
                    })
            }

    }
    viewModel.updateInitCount(1)
}