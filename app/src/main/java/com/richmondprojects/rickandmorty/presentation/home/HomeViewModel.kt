package com.richmondprojects.rickandmorty.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richmondprojects.rickandmorty.domain.repository.Repository
import com.richmondprojects.rickandmorty.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private var curPage = 0

    var states by mutableStateOf(HomeScreenStates())

    private var searchJob: Job? = null

    init {
        getCharacters()
    }

    fun onEvents(events: HomeScreenEvents) {
        when (events) {
            is HomeScreenEvents.Refresh -> {
                getCharacters(fetchFromRemote = true)
            }
            is HomeScreenEvents.OnSearchQueryChanged -> {
                states = states.copy(searchQuery = events.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCharacters()
                }
            }
        }
    }

    fun getCharacters(
        query: String = states.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            states = states.copy(isLoading = true)
            repository.getCharacters(page = 1 + curPage, fetchFromRemote, query)
                .collect { results ->
                    when (results) {
                        is Resource.Success -> {
                            results.data?.let {
                                states = states.copy(
                                    results = it
                                )
                            }
                            curPage++

                        }
                        is Resource.Loading -> {
                            states = states.copy(
                                isLoading = results.isLoading
                            )
                        }
                        is Resource.Error -> {
                            states = states.copy(
                                results = emptyList(),
                                isLoading = false,
                            )
                        }
                    }
                }
        }
    }
}