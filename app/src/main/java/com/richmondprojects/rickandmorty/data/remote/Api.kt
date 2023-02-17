package com.richmondprojects.rickandmorty.data.remote

import com.richmondprojects.rickandmorty.data.remote.dto.CharactersDto
import com.richmondprojects.rickandmorty.data.remote.dto.ResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharactersDto

    @GET("character/{id}")
    suspend fun getCharacterInfo(
        @Path("id") id: Int
    ):ResultDto
}