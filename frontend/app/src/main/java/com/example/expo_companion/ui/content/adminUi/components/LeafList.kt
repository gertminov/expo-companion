package com.example.expo_companion.ui.content.adminUi.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.expo_companion.R
import com.example.expo_companion.data.Leaf
import com.example.expo_companion.ui.content.sharedBtns.PrimaryButton
import com.example.expo_companion.ui.viewModels.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LeafList(
    viewModel: HomeViewModel,
    leafs: List<Leaf>,
    onDelete: (Leaf) -> Unit,
    onReplace: (Leaf) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {

        val listState = rememberLazyListState()


        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(1F),
        ) {
            items(
                items = leafs,
                key = { leaf -> leaf.id }
            ) { leaf ->
                if (viewModel.popUpExchangeLeaf) {
                    ExchangeLeafPopUpContent(
                        R.string.admin_exchangeLeafHeader,
                        leaf,
                        viewModel
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val formattedLeafNr =  leaf.leafNr.toString().padStart(2, '0')
                    Box(Modifier.width(200.dp)) {

                        Text(
                            text = "Blatt $formattedLeafNr",
//                                modifier = Modifier.padding(30.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }


                    /* AdminButton(
                             onClick = { onDelete(leaf) },
                             btnText = "Blatt löschen",
                             Color.Unspecified
                     )*/


                    PrimaryButton(
                        onClick = {
                            viewModel.popUpExchangeLeaf = true },
                        title = "Blatt ersetzen",
                    )

                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            horizontalAlignment = Alignment.End
        ) {
            Button(
                onClick = { CoroutineScope(Dispatchers.Main).launch { listState.scrollToItem(0) } },
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .padding(20.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.up_arrow),
                    contentDescription = "navigate to end of list",
                )
            }
            Spacer(Modifier.padding(10.dp))
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        listState.scrollToItem(
                            leafs.size - 1
                        )
                    }
                },
                modifier = Modifier
                    .width(170.dp)
                    .height(90.dp)
                    .padding(20.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.down_arrow),
                    contentDescription = "navigate to top of list",
                )
            }
        }
    }

}
