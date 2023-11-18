package com.example.expo_companion.ui.content.mainUi.sharedComponents


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.data.Category


@Composable
fun ScanInstruction(category: Category) {
    //todo mit spacer statt padding machen
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(1100.dp)
    ) {
        Box(
            modifier =
            if (category != Category.HIDDEN)
                Modifier.width(1100.dp).height(2000.dp)
            else (Modifier
                .width(1100.dp).height(2000.dp).padding(top = 500.dp))
        ) {
            if (category != Category.HIDDEN) {
                Image(
                    painter = painterResource(id = R.drawable.scaninstruction),
                    contentDescription = "General Scaninstruction",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.scaninstruction_hidden),
                    contentDescription = "Hidden Scaninstruction",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(900.dp, 500.dp)
                )
            }
        }
    }
}


