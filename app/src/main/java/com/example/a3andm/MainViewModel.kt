package com.example.a3andm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3andm.api.ApiEndpoints
import com.example.a3andm.api.recettes.Recette
import com.example.a3andm.api.recettes.ReponseRecetteByName
import com.example.a3andm.api.recettes.RequeteRecetteByName
import com.example.a3andm.local.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainViewModel(private val httpClient: HttpClient, private val repository: Repository) : ViewModel() {
    private val _recipesByNameResponse: MutableStateFlow<ReponseRecetteByName> =
        MutableStateFlow(ReponseRecetteByName(0, null, null, emptyList()))
    val recipesByNameResponse: StateFlow<ReponseRecetteByName> = _recipesByNameResponse

    private val _recetteById = MutableStateFlow<Recette?>(null)
    val recetteById: StateFlow<Recette?> = _recetteById
    val setRecetteById: (Recette?) -> Unit = { recette -> _recetteById.value = recette }

    val page = 1

    fun fetchRecipesByName(name: String, page: Int) {
        viewModelScope.launch {
            println("fetchRecipesByName")
            try {
                val response: HttpResponse = httpClient.get("${ApiEndpoints.FetchRecettesByNameUrl}?page=${page}&query=${name}") {
                    contentType(ContentType.Application.Json)
                    header("Authorization", ApiEndpoints.AuthorizationToken)
                }
                println(response.toString())
                if (response.status.isSuccess()) {
                    println("success")

                    val recipes = Json.decodeFromString<ReponseRecetteByName>(response.body<String>())
                    _recipesByNameResponse.value = recipes
                    _recipesByNameResponse.value.results.forEach { recipe ->
                        saveRecipeLocally(
                            recipe
                        )
                    }
                } else {
                    println("failed")
                    fetchLocallySavedRecipes(page)
                }
            } catch (e: Exception) {
                println(e)
                fetchLocallySavedRecipes(page)
            }
        }
    }

    fun fetchById(id: Int) {
        viewModelScope.launch {
            println("fetchById")
            try {
                val response: HttpResponse =
                    httpClient.get("${ApiEndpoints.FetchRecettesByIdUrl}?id=$id") {
                        contentType(ContentType.Application.Json)
                        header("Authorization", ApiEndpoints.AuthorizationToken)
                    }
                if (response.status.isSuccess()) {
                    val recipe = Json.decodeFromString<Recette>(response.toString())
                    _recetteById.value = recipe
                    saveRecipeLocally(recipe)
                    println("success")
                } else {
                    println("failed")
                    getRecipeLocally(id)
                }
            } catch (e: Exception) {
                println(e)
                getRecipeLocally(id)
            }
        }
    }

    fun fetchLocallySavedRecipes(page: Int) {
        viewModelScope.launch {
            val locallySavedRecipes = repository.getRecettes(30, page)
            _recipesByNameResponse.value = ReponseRecetteByName(page, null, null, locallySavedRecipes)
        }
    }


    fun saveRecipeLocally(recette: Recette) {
        viewModelScope.launch {
            repository.insert(recette)
        }
    }

    fun getRecipeLocally(pk: Int) {
        viewModelScope.launch {
            val recette = withContext(Dispatchers.IO) {
                repository.getRecette(pk)
            }
            _recetteById.value = recette
        }
    }

}

