package com.example.a3andm.composants

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.a3andm.MainViewModel

@Composable
fun BarreDeRecherche(viewModel: MainViewModel) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { newValue ->
                query = newValue
            },
            label = { Text("Rechercher des recettes") },
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        )

        Button(
            onClick = {
                viewModel.fetchRecipesByName(query, 1)
            },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffF33698),
                contentColor = Color.White
            )
        ) {
            Text("Recherche")
        }
    }
}
