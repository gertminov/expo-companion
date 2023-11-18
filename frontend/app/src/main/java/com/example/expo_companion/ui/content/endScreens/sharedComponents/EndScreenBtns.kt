import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.expo_companion.R
import com.example.expo_companion.ui.content.sharedBtns.PrimaryButton
import com.example.expo_companion.ui.content.sharedBtns.SecondaryIconButton
import com.example.expo_companion.ui.content.sharedComponents.buttons.SecondaryButton
import com.example.expo_companion.ui.viewModels.EndScreenViewModel

@Composable
fun nextBtnEnd(
    viewModel: EndScreenViewModel,
    title: String,
    route: String
) {
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        SecondaryButton(
            onClick = { viewModel.onNextClicked(route) },
            title = title,
        )
    }
}

@Composable
fun prevBtnEnd(
    viewModel: EndScreenViewModel,
    title: String,
    route: String
) {
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        PrimaryButton(
            onClick = { viewModel.onNextClicked(route) },
            title = title,
        )
    }
}

@Composable
fun xPrevBtnEnd(
    viewModel: EndScreenViewModel,
    route: String
) {
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        SecondaryIconButton(
            onClick = { viewModel.onNextClicked(route) },
            imageVector = ImageVector.vectorResource(id = R.drawable.xbtn),
            size = 60
        )
    }
}

@Composable
fun EmailDecisionBtn(
    title: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.End
    ) {
        SecondaryButton(
            onClick = onClick,
            title = title,
        )
    }
}