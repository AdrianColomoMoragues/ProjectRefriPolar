package com.cambiame.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage


@Composable
fun ImageFromUrl(url: String, modifier: Modifier = Modifier){
    AsyncImage(model = "https://m.media-amazon.com/images/M/MV5BMDNkOTE4NDQtMTNmYi00MWE0LWE4ZTktYTc0NzhhNWIzNzJiXkEyXkFqcGdeQXVyMzQ2MDI5NjU@._V1_.jpg", contentDescription = "")
}