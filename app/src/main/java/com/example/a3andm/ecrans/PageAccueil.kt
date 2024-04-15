package com.example.a3andm.ecrans

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.a3andm.MainViewModel
import com.example.a3andm.composants.BarreDeRecherche
import com.example.a3andm.composants.ListeRecettes
import com.example.a3andm.composants.Categories

@Composable
fun PageAccueil(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    Column {
        BarreDeRecherche(viewModel = viewModel)
        Categories(viewModel = viewModel)
        Spacer(modifier = Modifier.height(10.dp))
        ListeRecettes(viewModel = viewModel, navController = navController)
    }
}
