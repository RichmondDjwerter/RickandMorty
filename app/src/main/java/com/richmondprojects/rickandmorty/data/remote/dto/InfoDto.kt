package com.richmondprojects.rickandmorty.data.remote.dto

data class InfoDto(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)