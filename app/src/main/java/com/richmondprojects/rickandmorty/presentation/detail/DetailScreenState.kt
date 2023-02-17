package com.richmondprojects.rickandmorty.presentation.detail

import com.richmondprojects.rickandmorty.domain.model.Results

data class DetailScreenState(
    val results: Results? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)