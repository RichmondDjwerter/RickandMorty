package com.richmondprojects.rickandmorty.domain.model

data class Results(
    val created: String,
    val episode: List<String> = emptyList(),
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location? = null,
    val name: String,
    val origin: Origin? = null,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
