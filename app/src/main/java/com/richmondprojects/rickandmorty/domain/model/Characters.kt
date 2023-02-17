package com.richmondprojects.rickandmorty.domain.model

import com.richmondprojects.rickandmorty.data.remote.dto.InfoDto
import com.richmondprojects.rickandmorty.data.remote.dto.ResultDto

data class Characters(
    val info: Info,
    val results: List<Results>
)
