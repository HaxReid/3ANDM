package com.example.a3andm.composants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.a3andm.MainViewModel

@Composable
fun Categories(viewModel: MainViewModel) {
    val categories = listOf(
        "Dessert", "Chicken", "Beef", "Pasta", "Soup", "Salad", "Vegetarian"
    )
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    Row {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories.size) { id ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    color = if (selectedCategory == categories[id]) Color(0xffF33698) else Color.White
                ) {
                    Button(
                        modifier = Modifier.height(45.dp),
                        onClick = {
                            selectedCategory = categories[id]
                            viewModel.fetchRecipesByName(selectedCategory, 1)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedCategory == categories[id]) Color(0xffF33698) else Color.White,
                            contentColor = if (selectedCategory == categories[id]) Color.White else Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = categories[id])
                    }
                }
            }
        }
    }
}
