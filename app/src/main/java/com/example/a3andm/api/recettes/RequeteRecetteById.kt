package com.example.a3andm.api.recettes

import kotlinx.serialization.Serializable

@Serializable
data class RequeteRecetteById(
    val id: Int
)