package com.example.a3andm.local

import com.example.a3andm.api.recettes.Recette
import com.example.a3andm.local.dao.RecetteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val recetteDao: RecetteDao) {
    suspend fun insert(recipe: Recette) = withContext(Dispatchers.IO) {
        recetteDao.insert(recipe)
    }

    suspend fun getRecette(pk: Int): Recette? = withContext(Dispatchers.IO) {
        recetteDao.getRecette(pk)
    }

    suspend fun getRecettes(limit: Int, page: Int): List<Recette> = withContext(Dispatchers.IO) {
        recetteDao.getRecettes(limit, page)
    }

    /*suspend fun getNumberOfRecettes(): Int = withContext(Dispatchers.IO) {
        recetteDao.getNumberOfRecettes()
    }*/
}