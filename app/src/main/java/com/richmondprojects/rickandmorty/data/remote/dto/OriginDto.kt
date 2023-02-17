package com.richmondprojects.rickandmorty.data.remote.dto

import com.richmondprojects.rickandmorty.domain.model.Origin

data class OriginDto(
    val name: String,
    val url: String
) {
    fun toOriginDomain(): Origin {
        return Origin(
            name = name,
            url = url
        )
    }
}