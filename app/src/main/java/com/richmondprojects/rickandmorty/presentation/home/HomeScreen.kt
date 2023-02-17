package com.richmondprojects.rickandmorty.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.richmondprojects.rickandmorty.presentation.destinations.DetailScreenDestination

@Composable
@Destination(start = true)
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.states.isRefreshing)
    val state = viewModel.states


    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.onEvents(HomeScreenEvents.OnSearchQueryChanged(it)) },
            placeholder = { Text(text = "Search...") },
            maxLines = 1,
            singleLine = true,
            trailingIcon = {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                )
        )
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.onEvents(HomeScreenEvents.Refresh) }) {


            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.results.size) {
                    if (it >= state.results.size - 1 && !state.endReached && !state.isLoading) {
                        viewModel.onEvents(HomeScreenEvents.Refresh)
                    }
                    val outcome = state.results[it]
                    ListContent(
                        results = outcome,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable { navigator.navigate(DetailScreenDestination(outcome.id)) }
                    )
                    if (it < state.results.size) {
                        Divider()
                    }
                }
            }
        }
    }
}