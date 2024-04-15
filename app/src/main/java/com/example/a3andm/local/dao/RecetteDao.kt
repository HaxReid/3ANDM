package com.example.a3andm.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a3andm.api.recettes.Recette

@Dao
interface RecetteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recette: Recette)

    @Query("SELECT * FROM recipes WHERE pk=:pk")
    fun getRecette(pk: Int): Recette?

    @Query("SELECT * FROM recipes LIMIT :limit OFFSET :page * :limit")
    fun getRecettes(limit: Int, page: Int): List<Recette>

    @Query("SELECT COUNT(*) FROM recipes")
    fun getNumberOfRecettes(): Int
}