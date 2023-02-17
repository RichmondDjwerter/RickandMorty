package com.richmondprojects.rickandmorty.data.remote.dto

import com.richmondprojects.rickandmorty.domain.model.Location

data class LocationDto(
    val name: String,
    val url: String
) {
    fun toLocationDomain(): Location {
        return Location(
            name = name,
            url = url
        )
    }
}