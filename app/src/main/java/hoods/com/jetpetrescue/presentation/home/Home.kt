package hoods.com.jetpetrescue.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import hoods.com.jetpetrescue.presentation.components.PetInfoItem
import hoods.com.jetpetrescue.presentation.components.TopBar
import hoods.com.jetpetrescue.presentation.viewModel.UiState
import hoods.com.jetpetrescue.utils.ResourceHolder

@Composable
fun HomeScreen(
    onSwitchClick: () -> Unit,
    onPetClick: (Int) -> Unit,
    onLoadNextPage: () -> Unit,
    onInfiniteScrollingChange: (Boolean) -> Unit,
    uiState: UiState,
) {
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar {
                onSwitchClick()
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            when (uiState.animals) {
                is ResourceHolder.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center),
                            color = Color.Cyan,
                            strokeWidth = 4.dp
                        )
                    }
                }

                is ResourceHolder.Success -> {
                    val petList = uiState.animals.data ?: emptyList()
                    itemsIndexed(petList) { index, item ->
                        PetInfoItem(
                            pet = item,
                            onPetItemClick = {
                                onPetClick.invoke(index)
                            }
                        )
                        LaunchedEffect(key1 = scrollState) {
                            if (
                                index >= petList.lastIndex &&
                                !uiState.isFetchingPet &&
                                uiState.startInfiniteScrolling
                            ) {
                                onLoadNextPage()
                            }
                        }
                    }
                    if(uiState.isFetchingPet) {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    item {
                        AnimatedVisibility(visible = uiState.loadMoreButtonVisible) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                TextButton(
                                    onClick = {
                                        onLoadNextPage.invoke()
                                        onInfiniteScrollingChange(true)
                                    }
                                ) {
                                    Text(text = "Load More Pets")
                                }
                            }
                        }
                    }
                }
                else -> {
                    uiState.animals.throwable?.printStackTrace()
                    item {
                        Text(
                            text = "Error Occurred",
                            color = MaterialTheme.colors.error
                        )
                    }
                }
            }
        }
    }

}

