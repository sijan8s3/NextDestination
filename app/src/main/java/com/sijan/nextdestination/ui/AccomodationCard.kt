package com.sijan.nextdestination.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.HideImage
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sijan.nextdestination.data.sample.attributes.Accommodation

@Composable
fun AccommodationCard(accommodation: Accommodation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        //elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = accommodation.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp

        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Stars,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(text = accommodation.rating.toString(), fontSize = 10.sp)

            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Default.AttachMoney,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            //Spacer(modifier = Modifier.width(4.dp))
            Text(text = accommodation.price_per_night.toString(), fontSize = 10.sp)

        }

        //Icon(imageVector = Icons.Default.Stars, contentDescription = null)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Bed,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "Type: ${accommodation.type}", fontSize = 10.sp)
        }

        CommaSeparatedText(accommodation.amenities)


    }
}

@Composable
fun CommaSeparatedText(amenities: List<String>, fontSize: Int = 10) {
    Row {
        for ((index, amenity) in amenities.withIndex()) {
            Text(text = amenity, fontSize = fontSize.sp)
            if (index < amenities.size - 1) {
                Text(text = ", ", fontSize = fontSize.sp)
            }
        }
    }
}