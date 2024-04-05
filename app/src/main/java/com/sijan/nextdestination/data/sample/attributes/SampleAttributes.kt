package com.sijan.nextdestination.data.sample.attributes

data class SampleAttributes(
    val accommodation: List<Accommodation>,
    val budget: Budget,
    val food: List<Food>,
    val transportation: Transportation,
    val warnings: List<String>
)