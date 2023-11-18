package com.example.expo_companion.ui.content.introduction.sharedComponents

import android.content.Context
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.expo_companion.R

// Creating a function to get string id
@Composable
@ReadOnlyComposable
fun textResource(@StringRes id: Int): CharSequence =
    LocalContext.current.resources.getText(id)

// Creating a function to display the styled text
@Composable
fun StyledText(text: CharSequence, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> getTextView(context) },
        update = {
            it.text = text
        })
}

fun getTextView(context: Context): TextView {
    val color = Integer.parseInt("bdbdbd", 16) + 0xFF000000
    val textView = TextView(context)
    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
    textView.setTextColor(ContextCompat.getColor(context, R.color.black))
    return textView
}