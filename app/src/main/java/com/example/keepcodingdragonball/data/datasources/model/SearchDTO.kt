package com.example.keepcodingdragonball.data.datasources.model

import com.squareup.moshi.Json

data class SearchDto (
    @Json(name = "name") val name: String = ""
)