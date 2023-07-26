package com.example.keepcodingdragonball.data.mappers

import com.example.keepcodingdragonball.data.datasources.local.model.HeroLocal
import com.example.keepcodingdragonball.data.datasources.remote.model.HeroDTO
import com.example.keepcodingdragonball.domain.model.Hero

fun HeroDTO.toDomain() = Hero(
    id = id ?: "",
    photo = photo ?: "",
    description = description ?: "",
    name = name ?: "",
    favorite = favorite ?: false,

)

fun HeroLocal.toDomain() = Hero(
    id = id ?: "",
    photo = photoUrl ?: "",
    description = description ?: "",
    name = name ?: "",
    favorite = favorite ?: false,
)

fun HeroDTO.toLocal() = HeroLocal(
    id = id ?: "",
    photoUrl = photo ?: "",
    description = description ?: "",
    name = name ?: "",
    favorite = favorite ?: false,
)

fun Hero.toLocal() = HeroLocal(
    id = id ?: "",
    photoUrl = photo ?: "",
    description = description ?: "",
    name = name ?: "",
    favorite = favorite ?: false,
)
