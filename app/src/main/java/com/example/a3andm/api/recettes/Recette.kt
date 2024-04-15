package com.example.a3andm.api.recettes

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "recipes")
data class Recette(
    @PrimaryKey
    val pk: Int,
    val title: String,
    val publisher: String,
    val featured_image: String,
    val rating: Int,
    val source_url: String,
    val description: String,
    val cooking_instructions: List<String>?,
    val ingredients: List<String>?,
    val date_added: String,
    val date_updated: String,
    val long_date_added: Long,
    val long_date_updated: Long
)