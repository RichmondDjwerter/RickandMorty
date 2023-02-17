package com.richmondprojects.rickandmorty.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultsEntity(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
