package com.example.a3andm.ecrans

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.a3andm.MainViewModel


@Composable
fun PageDetail(viewModel: MainViewModel = viewModel(), recipeId: Int) {
    val recipe = viewModel.recetteById.collectAsState().value

    LaunchedEffect(key1 = recipeId) {
        recipeId?.let { viewModel.fetchById(it) }
    }

    Surface(
        color = Color(0xffEADD35),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            recipe?.let { recipe ->
                Image(
                    painter = rememberAsyncImagePainter(recipe.featured_image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = recipe.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                recipe.ingredients?.forEach { ingredient ->
                    Text(
                        text = ingredient,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Updated ${recipe.date_updated} by ${recipe.publisher}",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
        }
    }
}
