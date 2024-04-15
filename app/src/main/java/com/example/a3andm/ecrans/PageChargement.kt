package com.example.a3andm.ecrans

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.a3andm.MainViewModel
import com.example.a3andm.R
import com.example.a3andm.Routes
import kotlinx.coroutines.delay

@Composable
fun PageChargement(navController: NavController, viewModel: MainViewModel)
        = Box(
    Modifier
        .fillMaxWidth()
        .fillMaxHeight()
) {

    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(Routes.Accueil.route)
    }

    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
        modifier = Modifier
            .align(Alignment.Center)
            .size(250.dp)
    )


    Text(
        text = "Avec Fastochemix, plus facile de cuisiner que de le prononcer",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(45.dp)
    )
}