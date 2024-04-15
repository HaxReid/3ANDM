package com.example.a3andm.api.recettes

import kotlinx.serialization.Serializable

@Serializable
data class RequeteRecetteByName(
    val page: Int?, val query: String
)