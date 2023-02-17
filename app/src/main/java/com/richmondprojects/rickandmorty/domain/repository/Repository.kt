package com.richmondprojects.rickandmorty.domain.repository

import com.richmondprojects.rickandmorty.domain.model.Results
import com.richmondprojects.rickandmorty.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCharacters(
        page: Int,
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Results>>>

    suspend fun getCharacterDetails(
        id: Int
    ): Resource<Results>
}