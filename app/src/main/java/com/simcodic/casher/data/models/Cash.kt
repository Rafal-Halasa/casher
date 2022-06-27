package com.simcodic.casher.data.models

import com.squareup.moshi.Json

data class Cash(
    @field:Json(name = "success") val success: Boolean = false,
    @field:Json(name = "timestamp") val timestamp: Int = 0,
    @field:Json(name = "base") val base: String = "",
    @field:Json(name = "rates") val rates:  Map<String, Double>? = mapOf(),
    @field:Json(name = "date") val date: String = ""
)
