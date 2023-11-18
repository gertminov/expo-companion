package com.example.expo_companion.ui.content.introduction.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.ui.content.introduction.sharedComponents.IntroNavButtons
import com.example.expo_companion.ui.content.introduction.sharedComponents.StyledText
import com.example.expo_companion.ui.content.introduction.sharedComponents.textResource
import com.example.expo_companion.ui.theme.GreyDefault
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

@Composable
fun IntroPageOverlay(
    viewModel: ContentPageViewModel,
    image: Int,
    headerText: Int,
    bodyText: Int,
    stringRoute: String,
    textPositionModifier: Modifier = Modifier
        .padding(horizontal = 100.dp, vertical = 185.dp)
        .offset(40.dp)
        .fillMaxSize(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    spacerModifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreyDefault)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Navbar in the Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier =   textPositionModifier,
                verticalArrangement,
                horizontalAlignment
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(41.dp),
                ) {
                    Text(
                        text = stringResource(id = headerText),
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center
                    )
//                    Spacer(modifier = spacerModifier)
                    StyledText(textResource(id = bodyText))
                }
            }
            IntroNavButtons(viewModel = viewModel, route = stringRoute)
        }
    }
}
