package com.example.expo_companion.ui.content.adminUi.screens

import WindowCenterOffsetPositionProvider
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import androidx.compose.ui.window.Popup
import com.example.expo_companion.R
import com.example.expo_companion.data.Leaf
import com.example.expo_companion.ui.content.sharedBtns.SecondaryIconButton
import com.example.expo_companion.ui.content.sharedComponents.buttons.SecondaryButton
import com.example.expo_companion.ui.theme.White
import com.example.expo_companion.ui.viewModels.HomeViewModel

@Composable
fun PopUpChangeLeaf(viewModel: HomeViewModel, leaf: Leaf, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier.background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Popup(
                popupPositionProvider = WindowCenterOffsetPositionProvider(),
                onDismissRequest = { viewModel.viewModelDecider() },
            ) {
                Surface(
                    border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(8.dp),
                    color = White,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 100.dp, top = 50.dp)
                    ) {
                        SecondaryIconButton(
                            onClick = { viewModel.viewModelDecider() },
                            imageVector = ImageVector.vectorResource(id = R.drawable.xbtn),
                            size = 60
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(
                                start = 150.dp,
                                top = 300.dp,
                                end = 150.dp,
                                bottom = 300.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        content()

                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                SecondaryButton(
                                    onClick = { viewModel.viewModelDecider() },
                                    title = "Abbrechen",
                                    modifier = Modifier.width(350.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExchangeLeafPopUpContent(headerText: Int, leaf: Leaf, viewModel: HomeViewModel) {
    PopUpChangeLeaf(viewModel = viewModel, leaf) {
        Text(
            text = stringResource(id = headerText),
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Halten Sie nun bitte ein neues Blatt an die Rückseite des Tablets",
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.pickleafs2),
            contentDescription = "how to collect leafs graphic",
            modifier = Modifier.size(250.dp),
            tint = Color.Unspecified
        )
    }
}