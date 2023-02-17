package com.richmondprojects.rickandmorty.data.mappers

import com.richmondprojects.rickandmorty.data.local.ResultsEntity
import com.richmondprojects.rickandmorty.data.remote.dto.ResultDto
import com.richmondprojects.rickandmorty.domain.model.Results

fun ResultDto.toResultsDomain(): Results {
    return Results(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        name = name,
        location = location.toLocationDomain(),
        origin = origin.toOriginDomain(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}

fun Results.toResultsEntity(): ResultsEntity {
    return ResultsEntity(
        created = created,
        gender = gender,
        id = id,
        image = image,
        name = name,
        species = species,
        status = status,
        type = type,
        url = url
    )
}
fun ResultsEntity.toResults():Results{
    return Results(
        created = created,
        gender = gender,
        id = id,
        image = image,
        name = name,
        species = species,
        status = status,
        type = type,
        url = url
    )
}