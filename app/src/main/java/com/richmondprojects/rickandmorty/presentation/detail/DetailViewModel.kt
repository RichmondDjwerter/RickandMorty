package com.richmondprojects.rickandmorty.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richmondprojects.rickandmorty.domain.repository.Repository
import com.richmondprojects.rickandmorty.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var states by mutableStateOf(DetailScreenState())

    init {
        viewModelScope.launch {
            val id = savedStateHandle.get<Int>("id") ?: return@launch

            val characterInfo = repository.getCharacterDetails(id)

            when (val result = characterInfo) {
                is Resource.Success -> {
                    result.data?.let {
                        states = states.copy(
                            results = it,
                            error = null,
                            isLoading = false
                        )
                    }
                }
                is Resource.Error -> {
                    states = states.copy(
                        results = null,
                        error = result.message,
                        isLoading = false
                    )
                }
                else -> {}
            }

        }
    }

}