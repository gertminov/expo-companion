package com.example.expo_companion.ui.content.introduction.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.introduction.sharedComponents.StyledText
import com.example.expo_companion.ui.content.introduction.sharedComponents.textResource

const val KEY_CONTENT_PAGE_INDEX = "CONTENT_PAGE_INDEX"

@Composable
fun IntroPickLeafs() {
    Text(
        text = stringResource(id = R.string.welcome_title),
        style = MaterialTheme.typography.h1,
        textAlign = TextAlign.Center
    )
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically , modifier = Modifier.padding(top = 0.dp, start = 120.dp, end = 20.dp, bottom = 10.dp)) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.pickleafs1),
            contentDescription = "how to collect leafs graphic",
            modifier = Modifier.size(250.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(200.dp))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.pickleafs2),
            contentDescription = "how to collect leafs graphic",
            modifier = Modifier.size(250.dp),
            tint = Color.Unspecified
        )
    }
    StyledText(textResource(id = R.string.welcome_text))
}