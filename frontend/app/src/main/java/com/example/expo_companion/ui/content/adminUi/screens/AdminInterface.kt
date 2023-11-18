package com.example.expo_companion.ui.content.adminUi.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.sharedBtns.PrimaryButton
import com.example.expo_companion.ui.content.sharedComponents.buttons.SecondaryButton
import com.example.expo_companion.ui.viewModels.HomeViewModel

@Composable
fun AdminInterface(
        viewModel: HomeViewModel,
        navigateToWelcomeScreen: String
) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
            modifier = Modifier
                .padding(50.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
                text = stringResource(id = R.string.admin_panel_titel),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
        )

        Column(modifier = Modifier.fillMaxSize().weight(1f)) {
            if (showDialog) {
                LeafList(viewModel, viewModel.leafs,viewModel::deleteLeaf, viewModel::replaceLeaf)
            }
        }

        Row(
                modifier = Modifier
                    .padding(0.dp, 50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SecondaryButton(
                    onClick = { showDialog = true },
                    title = stringResource(id = R.string.admin_panel_LeafList),
            )
            PrimaryButton(
                    onClick = { viewModel.onNextClicked(navigateToWelcomeScreen) },
                    title = stringResource(id = R.string.admin_neustarten),
            )
        }


    }
}



