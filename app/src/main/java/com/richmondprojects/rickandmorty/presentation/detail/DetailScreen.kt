package com.richmondprojects.rickandmorty.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ramcosta.composedestinations.annotation.Destination
import com.richmondprojects.rickandmorty.R

@Composable
@Destination
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.states
    if (state.error == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            state.results?.let {
                Card(modifier = Modifier.size(400.dp, 400.dp),
                    shape =MaterialTheme.shapes.large) {
                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.image)
                            .crossfade(true)
                            .error(coil.base.R.drawable.notification_bg)
                            .placeholder(R.drawable.ic_launcher_background)
                            .build(),
                        contentScale = ContentScale.Fit
                    )
                    Image(
                        painter = painter,
                        contentDescription = "Character Image",
                        contentScale = ContentScale.Fit
                    )
                }
                Text(
                    text = it.name,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    val color = when (it.status) {
                        "Alive" -> Color.Green
                        "Dead" -> Color.Red
                        else -> Color.Gray
                    }
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(color = color, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = it.status)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(text = it.species, fontSize = 18.sp)
                        Text(text = "Species", fontWeight = FontWeight.Light, fontSize = 10.sp)
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(text = it.gender, fontSize = 18.sp)
                        Text(text = "Gender", fontWeight = FontWeight.Light, fontSize = 10.sp)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                it.origin?.let { it1 -> Text(text = it1.name) }
                Text(text = "Origin", fontWeight = FontWeight.Light)
                Spacer(modifier = Modifier.height(8.dp))
                it.location?.let { it1 -> Text(text = it1.name) }
                Text(text = "Last Known Location", fontWeight = FontWeight.Light)

            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error
            )
        }
    }
}