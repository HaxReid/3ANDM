package com.example.a3andm.api.recettes

import kotlinx.serialization.Serializable

@Serializable
data class ReponseRecetteByName(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Recette>
)