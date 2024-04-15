package com.example.a3andm.composants

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.a3andm.MainViewModel
import com.example.a3andm.Routes
import com.example.a3andm.api.recettes.ReponseRecetteByName

@Composable
fun ListeRecettes(viewModel: MainViewModel, navController: NavController) {
    val response by viewModel.recipesByNameResponse.collectAsState(initial = ReponseRecetteByName(0, null, null, emptyList()))
    val recipes = response.results
    var query by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(query) {
        viewModel.fetchRecipesByName("",1)
    }

    LazyColumn {
        items(recipes.size) { index ->
            val recipe = recipes[index]

            Surface(
                color = Color(0xffffffff),
                modifier = Modifier
                    .height(180.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .size(width = 210.dp, height = 180.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(recipe.featured_image),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Column(
                        modifier = Modifier
                            .weight(2f),
                        verticalArrangement = Arrangement.Center
                    ) {

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = recipe.title,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xffF33698)
                            ),
                            onClick = {
                                navController.navigate(Routes.Details.route + "/${recipe.pk}")
                            }
                        ) {
                            Text(
                                text = "Eat me!",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
    }
}


