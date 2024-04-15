package com.example.a3andm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a3andm.ecrans.PageAccueil
import com.example.a3andm.ecrans.PageChargement
import com.example.a3andm.ecrans.PageDetail
import com.example.a3andm.local.AppDatabase
import com.example.a3andm.local.Repository
import com.example.a3andm.local.dao.RecetteDao
import com.example.a3andm.ui.theme._3ANDMTheme
import io.ktor.client.HttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appDatabase: AppDatabase = AppDatabase.getDatabase(this)
        val recetteDao: RecetteDao = appDatabase.recetteDao()
        val repository = Repository(recetteDao)
        val viewModel = MainViewModel(HttpClient(), repository)
        setContent {
            _3ANDMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Routes.Chargement.route) {
                        composable(Routes.Accueil.route) {
                            PageAccueil(
                                navController = navController, viewModel = viewModel
                            )
                        }
                        composable(Routes.Chargement.route) {
                            PageChargement(navController = navController, viewModel = viewModel)
                        }
                        composable("${Routes.Details.route}/{recipeId}") { navBackStackEntry ->
                            val recipeId =
                                navBackStackEntry.arguments?.getString("recipeId")?.toInt()
                            PageDetail(viewModel = viewModel, recipeId = recipeId!!)
                        }

                    }

                }
            }
        }
    }
}