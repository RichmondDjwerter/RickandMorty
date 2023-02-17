package com.richmondprojects.rickandmorty.presentation.home

import com.richmondprojects.rickandmorty.domain.model.Results

data class HomeScreenStates(
    val results: List<Results> = emptyList(),
//    val newResults: ArrayList<Results> = ArrayList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val endReached: Boolean = false
)
