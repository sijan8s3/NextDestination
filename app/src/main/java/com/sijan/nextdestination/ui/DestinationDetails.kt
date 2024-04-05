package com.sijan.nextdestination.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Webhook
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sijan.nextdestination.data.Destination
import com.sijan.nextdestination.data.sample.attributes.SampleAttributes

@Composable
fun DestinationDetails(
    destination: Destination,
    attributes: SampleAttributes,
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        item {
            Column(
                modifier = Modifier.padding(4.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = {
                        onCloseClicked()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
        item {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(destination.image)
                    .crossfade(true)
                    .build(),
                //placeholder = painterResource(R.drawable.baseline_fireplace_24),
                contentDescription = destination.name,
                modifier = Modifier
                    .height(400.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { Text(text = destination.name, fontSize = 20.sp, fontWeight = FontWeight.Bold) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Text(text = destination.description, fontSize = 16.sp) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { DestinationAttribute(icon = Icons.Default.LocationOn, text = destination.continent +"->"+destination.country) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            DestinationAttribute(
                icon = Icons.Default.MonetizationOn,
                text = destination.currency
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { DestinationAttribute(icon = Icons.Default.LocalDining, text = destination.language) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        // Add more attributes as needed
        item {
            Text(
                text = "Activities to do:",
                modifier = Modifier.padding(top= 4.dp),
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 12.sp
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                destination.activities.forEach {
                    Card(modifier = Modifier.padding(4.dp, 8.dp).weight(1f)) { Text(text = it) }
                }
            }
        }

        item {
            Text(
                text = "Local Dishes:",
                modifier = Modifier.padding(top= 4.dp),
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 12.sp
                ),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                destination.local_dishes.forEach {
                    Card(modifier = Modifier.padding(4.dp, 8.dp).weight(1f)) {
                        Text(text = it)
                    }
                }
            }
        }

        item {
            Text(
                text = "Top Attractions:",
                modifier = Modifier.padding(top= 4.dp),
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 12.sp
                ),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                destination.top_attractions.forEach {
                    Card(modifier = Modifier.padding(4.dp, 8.dp).weight(1f)) {
                        Text(text = it)
                    }
                }
            }
        }

        item {
            Text(
                text = "Warnings:",
                modifier = Modifier.padding(top= 4.dp),
                style = TextStyle(
                    fontWeight = Bold,
                    fontSize = 12.sp,
                    color = Color.Red
                ),
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                //horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                attributes.warnings.forEach {
                    //Card(modifier = Modifier.padding(4.dp, 8.dp).weight(1f)) {
                        Text(text = it)
                    //}
                }
            }
        }


//
//                attributes.transportation
//                attributes.warnings
//                attributes.food

        item {Text(
            text = "Accommodations:",
            modifier = Modifier.padding(top= 6.dp),
            style = TextStyle(
                fontWeight = Bold,
                fontSize = 12.sp,
                color = Color.Green
            ),
        )}
        items(attributes.accommodation) {
            item ->
                AccommodationCard(accommodation = item)

        }
    }
}

@Composable
fun DestinationAttribute(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}
