package com.example.expo_companion.ui.content.introduction.sharedComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.sharedBtns.SecondaryIconButton
import com.example.expo_companion.ui.viewModels.ContentPageViewModel

@Composable
fun NextBtn(
    viewModel: ContentPageViewModel,
    route: String
) {
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        Spacer(modifier = Modifier.weight(1f))
        SecondaryIconButton(
            onClick = { viewModel.closePopUps(); viewModel.onNextClicked(route) },
            imageVector = ImageVector.vectorResource(id = R.drawable.nextbtn),
            size = 90
        )
    }
}
