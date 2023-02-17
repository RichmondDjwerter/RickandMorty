package com.richmondprojects.rickandmorty.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.richmondprojects.rickandmorty.R
import com.richmondprojects.rickandmorty.domain.model.Results

@Composable
fun ListContent(
    results: Results,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxSize(), Arrangement.spacedBy(10.dp)) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(results.image)
                        .crossfade(true)
                        .error(coil.base.R.drawable.notification_bg)
                        .placeholder(R.drawable.ic_launcher_background)
                        .build(),
                    contentScale = ContentScale.Crop
                )
                Image(
                    painter = painter,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop
                )

                Column {
                    val color = when (results.status) {
                        "Alive" -> Color.Green
                        "Dead" -> Color.Red
                        else -> Color.Gray
                    }
                    Text(
                        text = results.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(color = color, shape = CircleShape)
                        )
                        Text(text = "${results.status} - ${results.species}")

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Gender: ${results.gender}")
                }
            }
        }
    }
}