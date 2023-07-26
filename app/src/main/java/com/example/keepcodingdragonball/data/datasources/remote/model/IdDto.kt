package com.example.keepcodingdragonball.data.datasources.remote.model

import com.squareup.moshi.Json

data class IdDto (
    @Json(name = "id") val id: String = ""
    )