package com.richmondprojects.rickandmorty.data.remote.dto

data class CharactersDto(
    val info: InfoDto,
    val results: List<ResultDto>
)