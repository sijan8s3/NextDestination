package com.sijan.nextdestination.data.sample.attributes

data class Accommodation(
    val amenities: List<String>,
    val name: String,
    val price_per_night: String,
    val rating: Double,
    val type: String
)