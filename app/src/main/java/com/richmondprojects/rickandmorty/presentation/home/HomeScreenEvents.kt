package com.richmondprojects.rickandmorty.presentation.home

sealed class HomeScreenEvents {

    object Refresh : HomeScreenEvents()

    data class OnSearchQueryChanged(val query: String) : HomeScreenEvents()
}
