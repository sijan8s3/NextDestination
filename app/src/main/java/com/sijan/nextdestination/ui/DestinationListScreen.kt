package com.sijan.nextdestination.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sijan.nextdestination.R
import com.sijan.nextdestination.data.Destination

@Composable
fun DestinationList(
    destinations: List<Destination>,
    modifier: Modifier = Modifier,
    onDestinationClicked: (Destination) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(destinations) {
            DestinationItem(destination = it) {
                onDestinationClicked(it)
            }
        }
    }
}

@Composable
fun DestinationItem(destination: Destination, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(destination.image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.baseline_fireplace_24),
            contentDescription = destination.name,
            modifier = Modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(8.dp)

        ) {


            Spacer(modifier = Modifier.width(2.dp))
            Text(text = destination.name, fontSize = 16.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Public,
                    contentDescription = "Continent Icon",
                    modifier = Modifier.size(18.dp) // Adjust size as needed
                )
                Text(
                    text = destination.continent + " / " + destination.country,
                    fontSize = 12.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.People,
                    contentDescription = "Population Icon",
                    modifier = Modifier.size(18.dp) // Adjust size as needed
                )
                Text(
                    text = destination.population,
                    fontSize = 12.sp
                )
            }

        }

    }
}

